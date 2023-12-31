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
import com.mendix.core.objectmanagement.member.MendixBoolean;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.systemwideinterfaces.core.meta.IMetaObject;
import com.mendix.webui.CustomJavaAction;
import com.mendix.systemwideinterfaces.core.IMendixObject;
import system.proxies.FileDocument;
import java.io.InputStream;

public class CopyFileDocument extends CustomJavaAction<java.lang.Boolean>
{
	private IMendixObject __sourceObject;
	private saml20.proxies.KeyStore sourceObject;
	private IMendixObject __destinationObject;
	private saml20.proxies.KeyStore destinationObject;

	public CopyFileDocument(IContext context, IMendixObject sourceObject, IMendixObject destinationObject)
	{
		super(context);
		this.__sourceObject = sourceObject;
		this.__destinationObject = destinationObject;
	}

	@java.lang.Override
	public java.lang.Boolean executeAction() throws Exception
	{
		this.sourceObject = this.__sourceObject == null ? null : saml20.proxies.KeyStore.initialize(getContext(), __sourceObject);

		this.destinationObject = this.__destinationObject == null ? null : saml20.proxies.KeyStore.initialize(getContext(), __destinationObject);

		// BEGIN USER CODE
		IContext context = this.getContext();
		if (sourceObject != null && destinationObject != null && isFileDocument(sourceObject.getMendixObject().getMetaObject()) && isFileDocument(destinationObject.getMendixObject().getMetaObject()))
		{
			boolean hasContents =  sourceObject.getHasContents();
			if (hasContents)
			{
				InputStream inputstream = Core.getFileDocumentContent(context, sourceObject.getMendixObject());
				if (inputstream != null)
				{
					Core.storeFileDocumentContent(context, destinationObject.getMendixObject(),  sourceObject.getMendixObject().getValue(context, FileDocument.MemberNames.Name.toString()), inputstream );
				}
			}
		}
		return true;
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 * @return a string representation of this action
	 */
	@java.lang.Override
	public java.lang.String toString()
	{
		return "CopyFileDocument";
	}

	// BEGIN EXTRA CODE
	private boolean isFileDocument(IMetaObject metaObject)
	{
		if (metaObject.getSuperName() == null || "".equals(metaObject.getSuperName()))
			return FileDocument.entityName.equals(metaObject.getName());
		return isFileDocument(Core.getMetaObject(metaObject.getSuperObject().getName()));
	}
	// END EXTRA CODE
}
