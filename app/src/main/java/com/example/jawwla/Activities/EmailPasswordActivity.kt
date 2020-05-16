package com.example.jawwla.Activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.jawwla.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_email_password.*

class EmailPasswordActivity : BaseActivity(), View.OnClickListener {

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_password)


        login_txt_register.setOnClickListener(View.OnClickListener {
            intent=  Intent(this,RegisterActivity::class.java)
            startActivity(intent)

        })

        forget.setOnClickListener(View.OnClickListener {
            intent = Intent(this,ResetPasswordActivity::class.java)
            startActivity(intent)
        })
        // Buttons
        login_btn.setOnClickListener(this)

        // [START initialize_auth]
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        // [END initialize_auth]
    }

    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
    // [END on_start_check_user]

    private fun createAccount(email: String, password: String) {
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }

        showProgressBar()

        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    val intent = Intent(this,MainActivity::class.java)

                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // [START_EXCLUDE]
                hideProgressBar()
                // [END_EXCLUDE]
            }
        // [END create_user_with_email]
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        if (!validateForm()) {
            return
        }

        showProgressBar()

        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    val intent = Intent(this,MainActivity::class.java)

                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // [START_EXCLUDE]
                if (!task.isSuccessful) {
                }
                hideProgressBar()
                // [END_EXCLUDE]
            }
        // [END sign_in_with_email]
    }

    private fun signOut() {
        auth.signOut()
        updateUI(null)
    }

    private fun sendEmailVerification() {
        // Disable button

        // Send verification email
        // [START send_email_verification]
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this) { task ->
                // [START_EXCLUDE]
                // Re-enable button

                if (task.isSuccessful) {
                    Toast.makeText(baseContext,
                        "Verification email sent to ${user.email} ",
                        Toast.LENGTH_SHORT).show()
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(baseContext,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT).show()
                }
                // [END_EXCLUDE]
            }
        // [END send_email_verification]
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = login_email.text.toString()
        if (TextUtils.isEmpty(email)) {
            login_email.error = "Required."
            valid = false
        } else {
            login_email.error = null
        }

        val password = login_password.text.toString()
        if (TextUtils.isEmpty(password)) {
            login_password.error = "Required."
            valid = false
        } else {
            login_password.error = null
        }

        return valid
    }

    private fun updateUI(user: FirebaseUser?) {
        hideProgressBar()
        if (user != null) {


            login_btn.visibility = View.VISIBLE


        } else {


            login_btn.visibility = View.VISIBLE

        }
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            R.id.login_btn -> signIn(login_email.text.toString(), login_password.text.toString())
        }
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}

