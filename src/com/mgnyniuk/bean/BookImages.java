package com.mgnyniuk.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.mgnyniuk.jpa.Book;

@ManagedBean
@ApplicationScoped
public class BookImages {

	private HomeFunc homeFunc;

	public StreamedContent getImage() throws IOException {
		try {
			homeFunc = (HomeFunc) InitialContext.doLookup("java:module/HomeFunc");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {
			String bookId = context.getExternalContext()
					.getRequestParameterMap().get("id");
			Book book = homeFunc.findBook(Integer.valueOf(bookId));
			return new DefaultStreamedContent(new ByteArrayInputStream(book.getCover()));
		}
	}
}
