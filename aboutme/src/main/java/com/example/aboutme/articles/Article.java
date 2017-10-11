package com.example.aboutme.articles;

import java.util.ArrayList;
import java.util.List;

public class Article {

	public String identifier;
	public String contents;
	public List<String> images = new ArrayList<String>();

	public String getIdentifier () {
		return identifier;
	}

	public String getContents () {
		return contents;
	}

	public List<String> getImages () {
		return images;
	}

	public void addImage ( String image ) {
		images.add( image );
	}

	public void deleteImage ( String image ) {
		images.remove( image );
	}
	
	@Override
	public String toString () {
		return "Article [identifier=" + identifier + ", contents=" + contents + ", images=" + images + "]";
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
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
		Article other = (Article) obj;
		if ( contents == null ) {
			if ( other.contents != null )
				return false;
		} else if ( !contents.equals( other.contents ) )
			return false;
		if ( images == null ) {
			if ( other.images != null )
				return false;
		} else if ( !images.equals( other.images ) )
			return false;
		if ( identifier == null ) {
			if ( other.identifier != null )
				return false;
		} else if ( !identifier.equals( other.identifier ) )
			return false;
		return true;
	}

}
