// This file was generated by Mendix Studio Pro.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package saml20.actions;

import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import com.mendix.webui.CustomJavaAction;
import saml20.implementation.SAMLRequestHandler;

public class DelegatedAuthentication extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __SAMLSessionParam;
	private saml20.proxies.SAMLSession SAMLSessionParam;
	private java.lang.String ResourceURL;

	public DelegatedAuthentication(IContext context, IMendixObject SAMLSessionParam, java.lang.String ResourceURL)
	{
		super(context);
		this.__SAMLSessionParam = SAMLSessionParam;
		this.ResourceURL = ResourceURL;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.SAMLSessionParam = this.__SAMLSessionParam == null ? null : saml20.proxies.SAMLSession.initialize(getContext(), __SAMLSessionParam);

		// BEGIN USER CODE
        SAMLRequestHandler.getInstance(getContext()).requestDelegatedAuthentication(this.SAMLSessionParam.getSessionID(), this.ResourceURL);

        return false;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "DelegatedAuthentication";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}