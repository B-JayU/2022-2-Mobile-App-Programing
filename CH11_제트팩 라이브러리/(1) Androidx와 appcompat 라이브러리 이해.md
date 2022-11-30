[11-1. 제트팩과 androidx 소개](https://www.notion.so/c052797addce4bba8c6ba467cbb8c258)

[11-2. appcompat 라이브러리](https://www.notion.so/Study-11-2d93d880d5474ccc9815e92a59f77a23)

1. [액션바](https://www.notion.so/Study-11-2d93d880d5474ccc9815e92a59f77a23)
    1. 액션바 숨기기
2. [업 버튼 설정](https://www.notion.so/Study-11-2d93d880d5474ccc9815e92a59f77a23)
    1. 메니페스트 파일 수정
    2. 메니페스트 파일 수정X
3. [메뉴 구성](https://www.notion.so/Study-11-2d93d880d5474ccc9815e92a59f77a23)
    1. 메뉴 추가하기
    2. menuItem 이벤트 처리
4. 리소스로 메뉴 구현(res)
5. 액션 뷰 이용
6. 툴바

### 11-1. 제트팩과 androidx 소개

- 앱을 개발하는 데 필요한 권장 아키텍처를 제공
- API 레벨의 호환성 문제를 해결함
- 플랫폼 API에서 제공하지 않는 다양한 기능을 제공

**androidx 라이브러리**

- androidx.appcompat : 앱의 API 레벨 호환성
- androidx.recyclerview : 목록 화면을 구성
- andoridx.viewpager2 : 스와이프로 넘기는 화면을 구성
- androidx.fragment : 액티비티처럼 동작하는 뷰
- androidx.drawerlayout : 서랍처럼 열리는 화면을 구성

### 11-2. appcompat 라이브러리

- appcompat 라이브러리
    - 안드로이드 앱의 화면을 구성하는 액티비티를 만들며, API 레벨의 호환성 문제를 해결
    
    ```python
    // appcompat 라이브러리 선언
    // gradle
    implementation 'androidx.appcompat:appcompat:1.3.1'
    
    // appcompat 라이브러리 사용
    import androidx.appcompat.app.AppCompatActivity
    class MainActivity : AppCompatActivity() {
    
    }
    ```
    
- 액션바
    - 화면 위쪽에 타이틀 문자열이 출력되는 영역
    - 내비게이션 아이콘, 액션 아이템, 오버플로 메뉴 등 다양한 요소를 출력할 수 있다.
    - 액션바 색상 설정
        - 테마 스타일은 res/values 디렉터리에 있는 **themes.xml 파일**에 선언
    - 액션바 숨기기 설정 두 가지 방식
        - 액션바 숨기기 방법1
            - NoActionBar 설정
            
            ```python
            <style name="Theme.AndroidLab" 
            	parent="Them일까e.MaterialComponents.DayNight.NoActionBar">
            
            </style>
            ```
            
        - 액션바 숨기기 방법2
            
            ```python
            <style name="Theme.AndroidLab" 
            	parent="Theme.MaterialComponents.DayNight.DarkActionBar">
            	
            	<item name="windowActionBar"> false </item>
            	<item name="windowNoTitle"> true </item>
            </style>
            ```
            

- 업 버튼 설정
    - 업 버튼이 무엇인가?
        
        Up버튼은 스크린 사이에서 계층적인 관계에 기반한 앱 내에서 내비게이션하곤 한다.
        
        예를 들어, 만일 스크린 A가 아이템리스트를 보여준다면, 그리고 스크린B를 유도할 수 있는 아이템을 선택한다면, 스크린 B는 스크린 A로 돌아갈 수 있는 버튼을 제공해야만 한다.
        
        만일 스크린이 앱에서 가장 최상위라면(다시말해 앱의 홈), Up버튼은 노출하지 않아도 된다.
        
    - 상위 액티비티인 MainActivity에서 TwoActivity 로의 이동이 전개되었다면, TwoActivity 에서는 상위 액티비티로 돌아가기 위한 업버튼이 제공되어야 한다
        
        ```python
        # 시스템에 인텐트를 전달하여, MainActivity에서는 특정 버튼을 클릭하였을 떄, TwoActivity로
        # 이동하는 버튼을 배치할 수 있다.
        
        binding.button.setOnClickListener {
        	val intent = Intent(this, TwoActivity::class.java)
        	startActivity(intent)
        }
        ```
        
        ```python
        # AndroidManifest.xml 에서 TowActivity에서 업 버튼 클릭시, 상위 MainActivity로 이동할 수 
        # 있도록 설정할 수 있다.
        
        <activity
        	android:name=".TwoActivity"
        	android:parentActivityName=".MainActivity">
        
        </activity>
        ```
        
        ```python
        # 업 버튼 클릭 시 자동으로 호출되는 함수 재정의
        
        override fun onSupportNavigateUp(): Boolean {
        	Log.d("kkang", "onSupportNavigateUp")
        	return super.onSupportNavigateUp()
        }
        ```
        
    - 매니페스트 파일에 parentActivityName 속성 선언 없이 코드에서 업 버튼 생성하기
        
        ```python
        # 액티비티 생성 시, 업으로 이동가능하도록 설정
        # onCreate 함수 내에
        
        class TwoActivity : AppCompatActivity() {
        		override fun onCreate(savedInstanceState: Bundle?) {
        			
        			supportActionBar?.setDisplayHomeAsUpEnabled(true)
        		}
        		override fun onSupportNavigateUp() : Boolean {
        			Log.d("kkang", "onSupportNavigateUp")
        			onBackPressed()
        			return super.onSupportNavigateUp()
        	}
        }
        ```
        
- 메뉴 구성
    - 액션바의 중요한 구성 요소, 액티비티 화면에서 사용자가 이벤트를 사용할 수 있다
    - 액티비티에 메뉴 추가하기
        - onCreateOptionsMenu() : 액티비티가 실행되면서 처음에 한 번만 호출
            - 액티비티가 생성되면서 처음 한 번만 호출(onCreate)
        - onPrepareOptionsMenu() : 액티비티 실행되면서 한 번 호출된 후, 오버플로 메뉴가 나타날 때마다 반복해서 호출
        
        ```python
        <MainActivity.kt>
        
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        	val menuItem1: MenuItem? = menu?.add(0, 0, 0, "menu1")
        	val menuItem2: MenuItem? = menu?.add(0, 1, 0, "menu2")
        	return super.onCreateOptionsMenu(menu)
        }
        
        abstract fun add(groupId: Int, itemId: Int, order: Int, title: CharSequence!): MenuItem!
        ```
        
    - 메뉴에서 이벤트 처리
        - 이벤트가 처리됐을 때, onOptionsItemSelected() 함수를 이용
        - 함수의 매개변수 : 클릭된 MenuItem 객체
        
        ```python
        override fun onOpitonsItemSelected(item: MenuItem): Boolean =
        	when (item.itemId) {
        		0 -> {
        			Log.d("kkang", "menu1 click")
        			true
        		}
        		1 -> {
        			Log.d("kkang", "menu2 click")
        			true
        		}
        		else -> super.onOptionsItemSelected(item)
        }
        ```
        

- 리소스로 메뉴 구현
    - 메뉴를 액티비티에서 함수를 통해 구현하지 않고
    - res 폴더 아래 menu 디렉터리에 생성하고 사용하는 방법
        - 메뉴 자체를 resource로 관리
    - menu_main.xml 구성해보기
        
        ```python
        <menu xmlns:android="http://schemas.android.com/apk/res/android"
        	xmlns:app="http://schemas.android.com/apk/res-auto">
        	
        	<item
        		android:id="@+id/menu1"
        		android:title="menu1" />
        	
        	<item
        		android:id="@+id/menu2"
        		android:icon="@android:drawable/ic_menu_add"
        		android:title="menu2"
        		app:showAsAction="always" />
        
        	<item
        		android:id="@+id/menu3"
        		android:icon="@android:drawable/ic_menu_search"
        		android:title="menu3"
        		app:showAsAction="ifRoom"/>
        </menu>
        ```
        
        - menuItem 마다
            - id, icon, title, showAsAction 을 설정할 수 있다
            - 속성 : showAsAction ⇒ 액션바에 아이콘으로 나타나게 하기
                - default never : 항상 오버플로 메뉴로 출력한다
                - IfRoom : 만약 액션바에 공간이 있다면 액션 아이템으로, 없다면 오버플로 메뉴로 출력
                - always : 항상 액션 아이템으로 출력합니다
        
    - menu_main.xml를 액티비티에 적용 ⇒ menuInflater를 이용한다
        
        ```python
        override fun onCreateOptionMenu(menu: Menu?): Boolean {
        	#menuInflater를 통해서, res/mene/menu_main 리소스를 menu
        	menuInflater.inflate(R.menu.menu_main, menu)
        	
        	# menu 를 액티비티 실행되면서 한번만 호출되도록 한다 => 메뉴로 등록
        	return super.onCreateOptionsMenu(menu)
        }
        ```
        
- 액션 뷰 이용
    - 액션바에서 제공하는 특별한 기능
        - 예를 들어, 검색하는 액션 뷰 라던지…(SearchView)
            
            ```python
            <item android:id="@+id/menu_search"
            	android:title="search"
            	app:showAsAction="always"
            	app:actionViewClass="androidx.appcompat.widget.SearchView"/>
            ```
            
            - 액션바에서는 검색기능을 수행하는 SearchView 라는 액션 뷰를 제공하고 있는데…
                - 일반 menuItem과 동일하게, 설정해주면 된다.
                - 다만, 어떠한 actionViewClass 인지 명시해주어여 한다.
                - 참고로 app:showAsAction 값이 always 이므로 항상 상위 액션바에 아이콘으로 나타남
    - 액션 뷰를 이용하려면,
        - 액션 뷰를 담고 있는, menu item을 불러오고,
        - menu item에 등록된 액션 뷰 객체를 불러오기
        
        ```python
        override fun onCreateOptionsMenu(menu: Menu): Boolean {
        	val inflater = menuInflater
        	inflater.inflate(R.menu.menu_main, menu)
        
        	val menuItem = menu?.findItem( )
        	val searchview = menuItem?.actionview as SearchView
        
        	serachview.setOnQueryTextListener(object:SearchView.OnQueryTextListener {
        		override fun onQueryTextChange(newText: String?): Boolean {
        			// 
        			return true
        		}
        		override fun onQueryTextSubmit(query: String?): Boolean {
        			//
        			return true
        		}
        	})
        return true
        }
        ```
        

- 툴바
    - 액션바 : 액티비티 창이 자동으로 출력하는 액티비티 구성요소
    - 툴바 : 개발자가 직접 제어하는 뷰
        - 테마 파일에서 액션바를 숨기고, 툴바를 등록할 수 있다
        
        ```python
        # res/layout/activity_main.xml
        
        <androidx.appcompat.widget.Toolbar
        	android:id="@+id/toolbar"
        	android:layout_width="match_parent"
        	android:layout_height="wrap_content"
        	style="@style/Widget.MaterialComponents.Toolbar.Primary" />
        ```
        
        ```python
        class MainActivity : AppCompatActivity() {
        	override fun onCreate(savedInstanceState: Bundle?) {
        		
        		var binding = ActivityMainBinding.inflate(layoutInflater)
        		# 기존에 있던 actionbar를 숨기고 toolbar를 보이게 한다
        		setSupportActionBar(binding.toolbar)
        	}
        }
        ```
