package com.example.cropdigital.register

import android.app.ActionBar
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cropdigital.R
import com.example.cropdigital.getHours
import com.example.cropdigital.network.ItemsRequest
import com.example.cropdigital.showDialog
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_registration.*


/**
 * A simple [Fragment] subclass.
 */
class RegistrationFragment : Fragment() {

    private val args: RegistrationFragmentArgs by navArgs()

    private lateinit var parcelaLayout: TextInputLayout
    private lateinit var typeTaskLayout: TextInputLayout
    private lateinit var commentLayout: TextInputLayout

    private lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registrationViewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        parcelaLayout = view.findViewById(R.id.parcela) as TextInputLayout
        typeTaskLayout = view.findViewById(R.id.typeTaskRegistration) as TextInputLayout
        commentLayout = view.findViewById(R.id.commentRegistration) as TextInputLayout
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hourRegistration.text = getHours()
        setHasOptionsMenu(true)
        registerObservers()
    }

    private fun registerObservers() {
        registrationViewModel.onSuccess.observe(viewLifecycleOwner, Observer { value -> value?.let { findNavController().navigateUp() } })
        registrationViewModel.onError.observe(viewLifecycleOwner, Observer {
            showDialog(
                requireContext(),
                "Error",
                "Ha ocurrido un error",
                "Ok")
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater = requireActivity().menuInflater
        inflater.inflate(R.menu.menu, menu)
        val save = menu.findItem(R.id.guardar)

        save.setOnMenuItemClickListener {
            if (!confirmInput()) {
                registrationViewModel.addItem(createRequest())
                findNavController().navigateUp()
            }
            true
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun validateParcela(): Boolean {
        val parcela: String = parcela.editText?.text.toString().trim()
        return if (parcela.isEmpty()) {
            parcelaLayout.error = "Este campo es obligatorio"
            false
        } else {
            parcelaLayout.error = null
            true
        }
    }

    private fun validateTypeTask(): Boolean {
        val typeTaskRegistration: String = typeTaskRegistration.editText?.text.toString().trim()
        return if (typeTaskRegistration.isEmpty()) {
            typeTaskLayout.error = "Este campo es obligatorio"
            false
        } else {
            typeTaskLayout.error = null
            true
        }
    }

    private fun validateComment(): Boolean {
        val comment: String = commentRegistration.editText?.text.toString().trim()
        return if (comment.isEmpty()) {
            commentLayout.error = "Este campo es obligatorio"
            false
        } else {
            commentLayout.error = null
            true
        }
    }

    private fun confirmInput(): Boolean =
        !validateParcela() || !validateTypeTask() || !validateComment()

    private fun createRequest(): ItemsRequest {
        val newIndex= args.lastIndex.inc()
        val parcela = parcela.editText?.text.toString().trim()
        val typeTask = typeTaskRegistration.editText?.text.toString().trim()
        val comment = commentRegistration.editText?.text.toString().trim()
        return ItemsRequest(newIndex,parcela, typeTask, comment)
    }
}
