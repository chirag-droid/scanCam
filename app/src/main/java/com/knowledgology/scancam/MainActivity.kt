package com.knowledgology.scancam

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

private const val REQUEST_CODE = 42069
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scanButton: Button = findViewById(R.id.button)
        scanButton.setOnClickListener{ scan() }
    }
    
    //great tutorial on handelling camera intent - https://youtu.be/DPHkhamDoyc
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //bitmap makes it faster to further work on it
            val imageView: ImageView = findViewById(R.id.imageView)
            val takenImage = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(takenImage)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun scan() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //check if the device can handle camera intent to prevent crashing
        if (takePictureIntent.resolveActivity(this.packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        } else {
            Toast.makeText(this, "Can't Open Camera", Toast.LENGTH_SHORT).show()
        }
    }

}