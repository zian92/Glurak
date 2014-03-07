package de.glurak.data;

public class Playqueue {
	Playlist 	playlist;
	Medium 		current;
	int 		index;
	
	public Playqueue(Playlist playlist,int index){
		this.setPlaylist(playlist);
		this.index=index;
		
	}
	
	public Playqueue(Playlist playlist){
		this(playlist,0);
		
		
	}
	
	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	
	public void add(Medium medium){
	 getPlaylist().addMedium(medium);	
	}
	
	public boolean isCurrent(int index){
		
		return(this.index==index);
	}
	
	public void removeMedium(Medium medium){
		/*if(medium==getCurrent()){
			this.index= (index)%this.getPlaylist().getMediumList().size();	
			
		}
		*/
		getPlaylist().getMediumList().remove(medium);	
		System.out.println(index);
	}
		
	 
		
	
	/**
	 * Gibt aktuelles Medium zurück
	 * @return Medium der MediumList and der Stelle index
	 */
	public Medium getCurrent() {
		return this.getPlaylist().getMediumList().get(this.index);
	}
	
	/** Setzt aktuelles Medium
	 * @param index des neuen aktuellen Mediums
	 */
	public void setCurrent(int index){
		this.index = index%this.getPlaylist().getMediumList().size();
		
	}
	/**
	 * Setzt index++
	 * Gibt nächstes Medium zurück
	 * @return medium an der Stelle index+1
	 */
	public Medium getNext() {
		this.index= (index+1)%this.getPlaylist().getMediumList().size();
		return this.getPlaylist().getMediumList().get(index);
	}

	/**
	 * Gibt vorheriges Medium zurück
	 * @return medium an der Stelle index-1
	 */
	public Medium getPrevious() {
		this.index--;
		if(index<0){index=this.getPlaylist().getMediumList().size()-1;}
		return this.getPlaylist().getMediumList().get(index);
	}


}
