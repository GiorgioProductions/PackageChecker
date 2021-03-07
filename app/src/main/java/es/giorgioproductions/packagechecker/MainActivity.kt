package es.giorgioproductions.packagechecker

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonCheck = findViewById<Button>(R.id.buttonCheck)
        val textCheck = findViewById<TextView>(R.id.textCheck)
        val packageNameEditText = findViewById<EditText>(R.id.packageNameEditText)
        val context = this
        buttonCheck.setOnClickListener {
            val pm: PackageManager = context.packageManager
            if (packageNameEditText.text.trim() != "") {
                if (isPackageInstalled(packageNameEditText.text.toString(), pm)) {
                    textCheck.text = getString(R.string.is_installed, packageNameEditText.text.toString())
                } else {
                    textCheck.text = getString(R.string.is_not_installed, packageNameEditText.text.toString())
                }
            } else {
                textCheck.text = getString(R.string.write_a_package_name)
            }
        }
    }

    private fun isPackageInstalled(packageName: String, packageManager: PackageManager): Boolean {
        return try {
            packageManager.getPackageInfo(packageName, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}