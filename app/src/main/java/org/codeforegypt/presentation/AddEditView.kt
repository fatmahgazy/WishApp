package org.codeforegypt.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost

import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import org.codeforegypt.wishinglist.R
import org.codeforegypt.model.Wish

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditView(
    id: Long,
    navController: NavController,
 viewModel : WishViewModel

) {
    val titleText = remember { mutableStateOf(R.string.add_Wish) }
    val wish = viewModel.getWishById(id).collectAsState(initial = Wish(0L, "", "")).value
    LaunchedEffect(key1 = id) {
        if (id != 0L) {
            viewModel.wishTitleState = wish.title
            viewModel.wishDescriptionState = wish.description
            titleText.value = R.string.update_Wish
        } else {
            viewModel.wishTitleState = ""
            viewModel.wishDescriptionState = ""
            titleText.value = R.string.add_Wish
        }
    }
    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = scaffoldState.snackbarHostState) },
        topBar = {
            AppBar(
                title = stringResource(id= titleText.value)
                ,
                onBackNavClick = {
                    navController.navigateUp()
                }
            )
        }
    )
    { PaddingValues ->
        Column (
            modifier = Modifier
                .padding(PaddingValues)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Spacer(modifier = Modifier.height(10.dp))
            WishTextFiled(
                label = "Title",
                value = viewModel.wishTitleState
            ) {
                viewModel.wishTitleChanged(it)
            }
            Spacer(modifier = Modifier.height(10.dp))
            WishTextFiled(
                label = "description",
                value = viewModel.wishDescriptionState
            ) {
                viewModel.wishDescriptionChanged(it)

        }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                    if (id != 0L){
                      viewModel.updateWish(
                            Wish(
                                id = id,
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )

                      )
                    }else{
                        //add wish
                        viewModel.addWish(
                            Wish(
                                id= 0L,
                                title = viewModel.wishTitleState,
                                description = viewModel.wishDescriptionState

                            )
                        )
                        snackMessage.value = "Wish has been created"
                    }
                }else{
                    snackMessage.value = "Enter fields to create a wish"

                }
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                }
                navController.navigateUp()
            }) {
                Text(
                    text = stringResource( titleText.value)
                )
            }
        }
    }
}


@Composable
fun WishTextFiled(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value= value,
            onValueChange = onValueChange,
            label = { Text(text = label, color = Color.Black) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
    }
}

@Preview(showBackground = true , showSystemUi =  true)
@Composable
private fun WishPreview() {

}