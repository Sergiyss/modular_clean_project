package ua.dev.webnauts.homs

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember


/**
 * Определаю конец списка
 * */
@Composable
fun LazyListState.isAtBottom(): Boolean {

    return remember(this) {
        derivedStateOf {
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            if (layoutInfo.totalItemsCount == 0) {
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()

                var count = 0
                /**
                 * lastVisibleItem.index будет возвращать колличесво видимых обьектов на экран
                 * а layoutInfo.totalItemsCount колличесво всех обьектов в списке
                 *
                 *
                 * Если у словии выполняется 10 - 5 > 1 То можно разделить
                 * и мы получаем что нужно отнять 2 от общего списка, чтобы сработала подгрузака данных
                 * */

                if(layoutInfo.totalItemsCount - lastVisibleItem.index > 1){
                    try{
                        count = layoutInfo.totalItemsCount / lastVisibleItem.index
                    }catch (e : ArithmeticException){
                        count = 1
                    }

                }

                if(layoutInfo.totalItemsCount > 9){
                    if(lastVisibleItem.index+1 == layoutInfo.totalItemsCount - count){
                        return@derivedStateOf true
                    }else{
                        false
                    }
                }else{
                    false
                }
            }
        }
    }.value
}