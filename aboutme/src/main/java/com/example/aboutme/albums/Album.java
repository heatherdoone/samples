package com.example.aboutme.albums;

import java.util.ArrayList;
import java.util.List;

import com.example.aboutme.pages.Page;

public class Album {

	public String title;
	public String description;
	List<Page> pageList = new ArrayList<Page>();
	
	public String getTitle () {
		return title;
	}


	public String getDescription () {
		return description;
	}

	
	public void addPage ( Page page )
			throws Exception {

		pageList.add( page );

	}

	public void removePage ( Page page )
			throws Exception {

		pageList.remove( page );

	}
	
	@Override
	public String toString () {
		return "Album [title=" + title + ", description=" + description + ", pageList=" + pageList + "]";
	}


	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((pageList == null) ? 0 : pageList.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}


	@Override
	public boolean equals ( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Album other = (Album) obj;
		if ( description == null ) {
			if ( other.description != null )
				return false;
		} else if ( !description.equals( other.description ) )
			return false;
		if ( pageList == null ) {
			if ( other.pageList != null )
				return false;
		} else if ( !pageList.equals( other.pageList ) )
			return false;
		if ( title == null ) {
			if ( other.title != null )
				return false;
		} else if ( !title.equals( other.title ) )
			return false;
		return true;
	} 
	
	
}
