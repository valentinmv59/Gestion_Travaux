
public class Travaux {
	private Integer id;
	private String libelle;
	private String descriptif;
	private Integer priorite;
	private Integer roomId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public Integer getPriorite() {
		return priorite;
	}
	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
}
