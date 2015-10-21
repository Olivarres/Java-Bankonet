package command;

import java.sql.SQLException;
import java.sql.Statement;

import com.bankonet.lib.DBManager;

import metier.ClientService;

public class InitCommand implements IHMCommand {
	private int id = 10;
	private String lib = ". Initlialiser la BDD";
	ClientService cs;
	
	public InitCommand(ClientService cs) {
		this.cs = cs;
	}
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getLib() {
		return lib;
	}

	@Override
	public void execute() {
		DBManager dbm = new DBManager();
		
		dbm.connectMySQL();
		try {
			Statement state = dbm.connection.createStatement();
			int bla = state.executeUpdate("INSERT INTO CLIENT(NOM,PRENOM,IDENTIFIANT,PWD) VALUES('Winchester','Dean','WinD', 'pwd')");
			bla = state.executeUpdate("INSERT INTO CLIENT(NOM,PRENOM,IDENTIFIANT,PWD) VALUES('Winchester','Sammy','WinS', 'pwd')");
			bla = state.executeUpdate("INSERT INTO CLIENT(NOM,PRENOM,IDENTIFIANT,PWD) VALUES('Dunno','Ellen','DunnE', 'pwd')");
			bla = state.executeUpdate("INSERT INTO CLIENT(NOM,PRENOM,IDENTIFIANT,PWD) VALUES('Dunno','Bobby','DunnB', 'pwd')");
			bla = state.executeUpdate("INSERT INTO CLIENT(NOM,PRENOM,IDENTIFIANT,PWD) VALUES('Dunno','Jo','DunnJ', 'pwd')");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		

	}

}
