package com.occlass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.occlass.beans.FichierSSTitre;
import com.occlass.beans.PartSSTitre;
import com.occlass.exception.DatabaseException;

public class FichierSSTitreDAOImpl implements FichierSSTitreDAO {
	
	private DaoFactory daoFactory;
	private final String SQL_ENREGISTRER_FICHIER = "INSERT INTO FICHIERSSTITRE(NOMFICHIER,EMPLACEMENTORIGINE) VALUES(?,?);";
	private final String SQL_ENREGISTRER_LIGNES_SS_TITRE = "INSERT INTO DETAILSSTITRE(idFichierSSTitre,numLigne,contenu,debut,fin)"
			+ " values (?,?,?,?,?);";
	private final String SQL_GET_ALL_FICHIER = "SELECT * FROM FICHIERSSTITRE";
	private final String SQL_GET_FICHIER = "SELECT * FROM FICHIERSSTITRE WHERE idfichiersstitre = ?";
	private final String SQL_GET_DETAIL_FICHIER = "SELECT * FROM detailsstitre where idfichiersstitre = ?";
	
	public FichierSSTitreDAOImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void enregistrerFichier(FichierSSTitre fichier) {
		Connection conn = null;
		PreparedStatement statement = null;
		try {
			conn = daoFactory.getConnection();
			conn.setAutoCommit(false);
			statement = conn.prepareStatement(SQL_ENREGISTRER_FICHIER,Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, fichier.getTitre());
			statement.setString(2, fichier.getEmplacementOrigine());
			int idFichier = statement.executeUpdate();
			if (idFichier == 0) {
				throw new DatabaseException("Erreur lors de la création du fichier dans la table");
			}
			ResultSet rsKeys = statement.getGeneratedKeys();
			int key = 0;
			while(rsKeys.next()) {
				key = rsKeys.getInt(1);
			}
			statement.close();
			statement = conn.prepareStatement(SQL_ENREGISTRER_LIGNES_SS_TITRE);
			statement.setInt(1, key);
			for (PartSSTitre part : fichier.getDetailFichier()) {
				statement.setInt(2, part.getNumLigne());
				statement.setString(3, part.getSsTitre());
				statement.setString(4, part.getDateDebut());
				statement.setString(5, part.getDateFin());
				statement.executeUpdate();
			}
			conn.commit();
		} catch (SQLException | DatabaseException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	@Override
	public FichierSSTitre getFichier(int id) {
		Connection conn = null;
		PreparedStatement statement = null;
		FichierSSTitre fichier = null;
		PartSSTitre detail = null;
		List<PartSSTitre> listSSTitre = null;
		try {
			conn = daoFactory.getConnection();
			statement = conn.prepareStatement(SQL_GET_FICHIER);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			fichier = new FichierSSTitre();
			while (rs.next()) {
				fichier.setId(id);
				fichier.setTitre(rs.getString("NomFichier"));
				fichier.setEmplacementOrigine(rs.getString("EmplacementOrigine"));
			}
			statement.close();
			rs.close();
			statement = conn.prepareStatement(SQL_GET_DETAIL_FICHIER);
			statement.setInt(1, id);
			rs = statement.executeQuery();
			listSSTitre = new ArrayList<PartSSTitre>();
			while(rs.next()) {
				detail = new PartSSTitre();
				detail.setDateDebut(rs.getString("debut"));
				detail.setDateFin(rs.getString("fin"));
				detail.setId(rs.getInt("iddetailsstitre"));
				detail.setNumLigne(rs.getInt("numligne"));
				detail.setSsTitre(rs.getString("contenu"));
				listSSTitre.add(detail);
			}
			fichier.setDetailFichier(listSSTitre);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return fichier;
	}

	@Override
	public List<FichierSSTitre> getAllFichiers() {
		Connection conn = null;
		PreparedStatement statement = null;
		List<FichierSSTitre> listFichier = null;
		try {
			listFichier = new ArrayList<FichierSSTitre>();
			conn = daoFactory.getConnection();
			statement = conn.prepareCall(SQL_GET_ALL_FICHIER);
			ResultSet rs = statement.executeQuery();
			FichierSSTitre fichier = null;
			while (rs.next()) {
				fichier = new FichierSSTitre();
				fichier.setId(rs.getInt(1));
				fichier.setEmplacementOrigine(rs.getString(3));
				fichier.setTitre(rs.getString(2));
				listFichier.add(fichier);
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return listFichier;
	}

}
