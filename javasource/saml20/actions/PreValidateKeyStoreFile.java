// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package saml20.actions;

import com.mendix.core.Core;
import com.mendix.logging.ILogNode;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import saml20.implementation.common.Constants;
import saml20.implementation.security.KeyStoreHelper;
import java.io.InputStream;
import java.util.Enumeration;

public class PreValidateKeyStoreFile extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __keyStoreObj;
	private saml20.proxies.KeyStore keyStoreObj;
	private saml20.proxies.EncryptionKeyLength encryptionKeyLength;

	public PreValidateKeyStoreFile(IContext context, IMendixObject keyStoreObj, java.lang.String encryptionKeyLength)
	{
		super(context);
		this.__keyStoreObj = keyStoreObj;
		this.encryptionKeyLength = encryptionKeyLength == null ? null : saml20.proxies.EncryptionKeyLength.valueOf(encryptionKeyLength);
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.keyStoreObj = this.__keyStoreObj == null ? null : saml20.proxies.KeyStore.initialize(getContext(), __keyStoreObj);

		// BEGIN USER CODE
		String aliasName = keyStoreObj.getAlias();
		String password = keyStoreObj.getPassword();
		boolean isAliasFindInKeyStore = false;
		if (password != null) {
			password = KeyStoreHelper.decrypt(password, getContext());
		}

		try (InputStream inStr = Core.getFileDocumentContent(this.getContext(), __keyStoreObj)) {
			java.security.KeyStore ks = KeyStoreHelper.getKeystore(inStr, encryptionKeyLength, password);
			Enumeration<String> aliases = ks.aliases();
			while (aliases.hasMoreElements()) {
				String name = aliases.nextElement();
				if (name.equals(aliasName)) {
					isAliasFindInKeyStore = true;
					break;
				}
			}
		} catch (Exception e) {
			Core.getLogger(Constants.LOGNODE).error(e.getMessage());
			return false;
		}
		if (!isAliasFindInKeyStore) {
			Core.getLogger(Constants.LOGNODE).error("Alias is not found in key store file");
		}
		return isAliasFindInKeyStore;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "PreValidateKeyStoreFile";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}