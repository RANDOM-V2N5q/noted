package com.example.noted.authentication

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.noted.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.authentication_fragment.*
import java.util.concurrent.Executor

class AuthenticationFragment : Fragment() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private lateinit var viewModel: AuthenticationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.authentication_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        executor = ContextCompat.getMainExecutor(requireContext())

        biometricPrompt = BiometricPrompt(this@AuthenticationFragment, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                val message = getString(R.string.auth_error) + " " + errString
                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                val message = getString(R.string.auth_failed)
                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                val message = getString(R.string.auth_successful)
                Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
                goToNoteList()
            }
        })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(getString(R.string.title))
            .setSubtitle(getString(R.string.subtitle))
            .setNegativeButtonText(getString(R.string.negative_button))
            .build()

        authentication_button.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }

    private fun goToNoteList() {
        findNavController().navigate(R.id.action_authenticationFragment_to_notesList)
    }
}