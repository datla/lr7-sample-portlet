package com.liferay.docs.guestbook2.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Component;

import com.liferay.docs.guestbook2.constants.GuestbookPortlet2PortletKeys;
import com.liferay.docs.guestbook2.model.Entry;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * @author raja
 */
@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=my-guestbook2-project Portlet",
		"javax.portlet.init-param.template-path=/", "javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GuestbookPortlet2PortletKeys.GuestbookPortlet2,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class GuestbookPortlet2Portlet extends MVCPortlet {

	private static final String PREFS_IDENTIFIER = "reftype-entries";

	public void addEntry(ActionRequest request, ActionResponse response) {
		try {
			PortletPreferences prefs = request.getPreferences();
			String[] refTypeEntries = prefs.getValues(PREFS_IDENTIFIER, new String[1]);
			ArrayList<String> entries = new ArrayList<String>();
			if (refTypeEntries != null) {
				entries = new ArrayList<String>(Arrays.asList(prefs.getValues(PREFS_IDENTIFIER, new String[1])));
			}
			String userName = ParamUtil.getString(request, "type");
			String message = ParamUtil.getString(request, "description");
			String entry = userName + "^" + message;
			entries.add(entry);
			String[] array = entries.toArray(new String[entries.size()]);
			prefs.setValues(PREFS_IDENTIFIER, array);
			try {
				prefs.store();
			} catch (IOException ex) {
				Logger.getLogger(GuestbookPortlet2Portlet.class.getName()).log(Level.SEVERE, null, ex);
			} catch (ValidatorException ex) {
				Logger.getLogger(GuestbookPortlet2Portlet.class.getName()).log(Level.SEVERE, null, ex);
			}
		} catch (ReadOnlyException ex) {
			Logger.getLogger(GuestbookPortlet2Portlet.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException, IOException {
		System.out.println("Render method called..");
		PortletPreferences prefs = renderRequest.getPreferences();
		String[] refTypeEntries = prefs.getValues(PREFS_IDENTIFIER, new String[1]);
		if (refTypeEntries != null) {
			List<Entry> entries = parseEntries(refTypeEntries);
			renderRequest.setAttribute("entries", entries);
		}
		super.render(renderRequest, renderResponse);
	}

	private List<Entry> parseEntries(String[] refTypeEntries) {
		ArrayList<Entry> entries = new ArrayList<>();
		for (String entry : refTypeEntries) {
			if (entry != null) {
				String[] parts = entry.split("\\^", 2);
				Entry gbEntry = new Entry(parts[0], parts[1]);
				entries.add(gbEntry);
			}
		}
		return entries;
	}
}