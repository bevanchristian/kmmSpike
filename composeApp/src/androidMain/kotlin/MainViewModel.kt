import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


// Extend ViewModel ini untuk membantu memanage lifcycle dari class MainViewModel biar nda ke destroy selapa scope nya belom selesai
// kalau fragment nya hancur karena di rotate maka nanti fragment baru akan di assing ke mainviewmodel
// ini kalau di swift sudah automate List<RocketLaunch>
class MainViewModel: ViewModel() {
    private val _greetingList = MutableStateFlow<List<RocketLaunch>>(listOf())
    val greetingList: StateFlow<List<RocketLaunch>> get() = _greetingList


    init {
        // ini kayak task apapun function yang suspend maka harus didalam coroutine scope
        viewModelScope.launch {
//            val a = RocketComponent().getRockeTFlow()
//            _greetingList.update { list -> list + a }
            RocketComponent().getRockeTFlow().collect { data ->
                _greetingList.update { list -> list + data }
            }
        }
    }

}