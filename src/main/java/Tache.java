import java.sql.ResultSet;

public class Tache {
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
	
	public static Tache rsetToTache(ResultSet rs) {
		if (rs == null) {
			throw new IllegalArgumentException("Le resultset ne peut pas être null");
		}

		final Tache tache = new Tache();

		try {
			final Integer id = rs.getInt("idtravaux");
			final String libelle = rs.getString("Libelle_travaux");
			final String descriptif = rs.getString("Descriptif");
			final Integer priorite = rs.getInt("Priorite");
			final Integer roomId = rs.getInt("mrbs_room_id");

			tache.setId(id);
			tache.setLibelle(libelle);
			tache.setDescriptif(descriptif);
			tache.setPriorite(priorite);
			tache.setRoomId(roomId);

		} catch (Exception e) {
			System.out.println(e);
		}

		return tache;
	}
}
