package com.android.petprog.gadsleaderboard.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.android.petprog.gadsleaderboard.R
import com.android.petprog.gadsleaderboard.model.SubmitApi
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SubmitActivity : AppCompatActivity() {

    private lateinit var submitButton: Button
    private lateinit var emailInput: EditText
    private lateinit var firstNameInput: EditText
    private lateinit var lastNameInput: EditText
    private lateinit var projectLinkInput: EditText

    private lateinit var customDialog: Dialog
    private lateinit var successDialog: Dialog
    private lateinit var closeButton: MaterialButton
    private lateinit var yesButton: MaterialButton
    private lateinit var handler: Handler

    private var width = 0
    private var height = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        val toolbar: Toolbar = findViewById(R.id.toolbar_submit_layout)
        submitButton = findViewById(R.id.submit_button)
        emailInput = findViewById(R.id.text_input_email)
        firstNameInput = findViewById(R.id.text_input_first_name)
        lastNameInput = findViewById(R.id.text_input_last_name)
        projectLinkInput = findViewById(R.id.text_input_project_link)

        val metrics = resources.displayMetrics
        width = metrics.widthPixels
        height = metrics.heightPixels

        toolbar.setNavigationIcon(R.drawable.ic_baseline_keyboard_backspace_24)

        setSupportActionBar(toolbar)

        customDialog = Dialog(this)
        successDialog = Dialog(this)

        val retrofitSubmit = Retrofit.Builder()
            .baseUrl("https://docs.google.com/forms/d/e/")
            .build()

        val googleForms: SubmitApi = retrofitSubmit.create(
            SubmitApi::class.java
        )

        submitButton.setOnClickListener {
            if (firstNameInput.text.toString().isEmpty() || lastNameInput.text.toString()
                    .isEmpty() || emailInput.text.toString()
                    .isEmpty() || projectLinkInput.text.toString().isEmpty()
            ) {
                Toast.makeText(this@SubmitActivity, "Please complete details", Toast.LENGTH_SHORT)
                    .show()
            } else {
                customDialog.setContentView(R.layout.custom_are_you_sure_screen)
                closeButton = customDialog.findViewById(R.id.close_button)
                yesButton = customDialog.findViewById(R.id.yes_button)
                closeButton.setOnClickListener { view1: View? -> customDialog.dismiss() }
                customDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                customDialog.window!!.setLayout(6 * width / 7, 4 * height / 5)
                customDialog.show()
                yesButton.setOnClickListener { view12: View? ->
                    val email = emailInput.text.toString()
                    val firstName = firstNameInput.text.toString()
                    val lastName = lastNameInput.text.toString()
                    val projectLink = projectLinkInput.text.toString()
                    val completeGoogleForms =
                        googleForms.completeGoogleForm(email, firstName, lastName, projectLink)
                    completeGoogleForms.enqueue(callBack)
                }
            }

        }
    }

    private val callBack: Callback<Void?> = object : Callback<Void?> {
        override fun onResponse(
            call: Call<Void?>,
            response: Response<Void?>
        ) {
            successDialog.setContentView(R.layout.custom_successful)
            successDialog.window
                ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            successDialog.window!!.setLayout(6 * width / 7, 4 * height / 5)
            successDialog.show()
            handler = Handler()
            handler.postDelayed({
                successDialog.cancel()
                successDialog.dismiss()
                customDialog.dismiss()
            }, 1000)
        }

        override fun onFailure(
            call: Call<Void?>,
            t: Throwable
        ) {
            successDialog.setContentView(R.layout.custom_not_successful)
            successDialog.window
                ?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            successDialog.window!!.setLayout(6 * width / 7, 4 * height / 5)
            successDialog.show()
            handler = Handler()
            handler.postDelayed({
                successDialog.cancel()
                successDialog.dismiss()
            }, 1000)
        }
    }

}