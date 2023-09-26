package com.example.data;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Veiculo;

public class VeiculoDao {

    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
    private final String USER = "RM552283";
    private final String PASS = "170204";
    
    public void inserir(Veiculo veiculo) throws SQLException{
        var con = DriverManager.getConnection(URL, USER, PASS);

        var ps = con.prepareStatement("INSERT INTO TBL_CONTATOS_RM552283 VALUES (?, ?, ?, ?)");
        ps.setString(1, veiculo.getMarca());
        ps.setString(2, veiculo.getModelo());
        ps.setInt(3, veiculo.getAno());
        ps.setBigDecimal(4, veiculo.getValor());

        ps.executeUpdate();
        con.close();
    }

    public List<Veiculo> buscarTodos() throws SQLException{
        var veiculos = new ArrayList<Veiculo>();
        var con = DriverManager.getConnection(URL, USER, PASS);
        var rs = con.createStatement().executeQuery("TBL_CONTATOS_RM552283");

        while(rs.next()){
            veiculos.add(new Veiculo(
                rs.getString("marca"), 
                rs.getString("modelo"), 
                rs.getInt("ano"), 
                rs.getBigDecimal("valor")
            ));
        }
        con.close();
        return veiculos;
    }
}
