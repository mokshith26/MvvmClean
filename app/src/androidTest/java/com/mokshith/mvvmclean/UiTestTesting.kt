package com.mokshith.mvvmclean

import androidx.compose.ui.test.junit4.createComposeRule
import com.mokshith.mvvmclean.presentation.theme.menuList.MenuListScreen
import org.junit.Rule
import org.junit.Test

class UiTestTesting   {

    @get:Rule
    val rule = createComposeRule()
    
     @Test
     fun showManuList(){
         rule.setContent {
             MenuListScreen()

         }
     }
}