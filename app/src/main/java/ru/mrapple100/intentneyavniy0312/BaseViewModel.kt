package ru.mrapple100.intentneyavniy0312


import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.mrapple100.intentneyavniy0312.databinding.ActivityMainBinding


class BaseViewModel: ViewModel() {

    val urlLiveData = MutableLiveData<String>("https://www.mirea.ru")

    var activity: AppCompatActivity? = null
    var binding: ActivityMainBinding? = null
    var launcher: ActivityResultLauncher<Intent>? = null
    fun initViewModel(activity: AppCompatActivity,binding: ActivityMainBinding){
        this.activity = activity
        this.binding = binding

        launcher = activity?.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                if(it.resultCode == Activity.RESULT_OK){
                    val uri = it.data?.data

                    // Обработка выбранного изображения
                    uri?.let {
                        val imageView: ImageView = binding?.Image!!
                        imageView.setImageURI(it)
                    }

                }
            }
        )
    }

    fun clickNumber(){
        val uri = Uri.parse("tel:8(800)-555-35-35")
        val intent = Intent(Intent.ACTION_DIAL,uri)
        activity?.startActivity(intent)
    }
    fun clickURL(){
        val uri = Uri.parse(urlLiveData.value)
        val intent =Intent(Intent.ACTION_VIEW,uri)
        activity?.startActivity(intent)
    }
    fun chooseImage(){
        val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        launcher?.launch(intent)
    }
    fun clickEmail(){
        val addresses = arrayOf<String>("mail@mail.ru","mail2@mail.ru")
        val uri = Uri.parse("mailto: ")
        val intent = Intent(Intent.ACTION_SENDTO,uri)
        intent.putExtra(Intent.EXTRA_EMAIL,addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT,"Заголовок письма")
        intent.putExtra(Intent.EXTRA_TEXT,"тело письма")
        activity?.startActivity(intent)
    }








}