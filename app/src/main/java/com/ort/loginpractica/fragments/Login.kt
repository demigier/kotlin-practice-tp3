package com.ort.loginpractica.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ort.loginpractica.R
import com.ort.loginpractica.entities.User
import com.ort.loginpractica.viewmodels.LoginViewModel

class Login : Fragment() {

    companion object {
        fun newInstance() = Login()
    }

    private lateinit var viewModel: LoginViewModel
    lateinit var v: View

    lateinit var btnSend: Button
    lateinit var txtEmail: EditText
    lateinit var txtPass: EditText

    var users: MutableList<User> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.login_fragment, container, false)

        txtEmail = v.findViewById(R.id.txtEmail)
        txtPass = v.findViewById(R.id.txtPass)
        btnSend = v.findViewById(R.id.btnSend)

        return v
    }

    override fun onStart() {
        super.onStart()

        users.add(User("daritopeladito@gmail.com","soydolape33"))
        users.add(User("carlosmenem@gmail.com","lohice"))
        users.add(User("manuteamo@gmail.com","manusoshermoso"))
        users.add(User("admin","admin"))

        btnSend.setOnClickListener{
            if(txtEmail.text.isNotEmpty() && txtPass.text.isNotEmpty()){
                var a = users.find { user ->
                    user.email == txtEmail.text.toString() && user.password == txtPass.text.toString()
                }
                if(a != null){
                    val action = LoginDirections.actionLoginToProfile(txtEmail.text.toString())
                    v.findNavController().navigate(action)
                }else{
                    Snackbar.make(v,"Usuario o contraseña incorrectos",Snackbar.LENGTH_SHORT).show()
                }

            }else{
                Snackbar.make(v,"No deje texto vacio",Snackbar.LENGTH_SHORT).show()
            }

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}