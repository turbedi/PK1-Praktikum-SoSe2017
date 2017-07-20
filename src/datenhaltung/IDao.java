package datenhaltung;

import java.util.List;

import fachlogik.Medium;

public interface IDao {
	void speichern(List<Medium> liste) throws PersistenzException;
	List<Medium> laden() throws PersistenzException;
}
