package com.example.sistemaDeReservasDeConsultas.dao.impl;

import com.example.sistemaDeReservasDeConsultas.dao.ConfiguracaoJDBC;
import com.example.sistemaDeReservasDeConsultas.dao.IDao;
import com.example.sistemaDeReservasDeConsultas.model.Paciente;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.LogManager.getLogger;

@Configuration
public class PacienteDaoH2 implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC = new ConfiguracaoJDBC("org.h2.Driver", "jdbc:h2:~/clinica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "");

    Connection connection = null;

    Statement st = null;

    final static Logger log = getLogger(PacienteDaoH2.class);

    @Override
    public Paciente salvar(Paciente paciente) throws SQLException {
        log.info("Abrindo conexão");

        String sqlInsert = String.format("INSERT INTO paciente (nome, sobrenome, endereco, rg, dataDeAlta) VALUES ('%s', '%s', '%s', '%s', '%s')", paciente.getNome(), paciente.getSobrenome(), paciente.getEndereco(), paciente.getRg(), paciente.getDataDeAlta());

        try{
            log.info("Cadastrando novo paciente: " + paciente.getNome() + " " + paciente.getSobrenome());

            connection = configuracaoJDBC.getConnection();

            st = connection.createStatement();
            st.execute(sqlInsert, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = st.getGeneratedKeys();

            if(rs.next()){
                paciente.setId(rs.getInt("id"));
            }
        }catch (SQLException e){
            log.error("Erro no cadastro de novo paciente: " + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
        return paciente;
    }

    @Override
    public List<Paciente> buscarTodos() throws SQLException {
        log.info("Abrindo conexão");

        String sqlSelect = "SELECT * FROM paciente";

        List<Paciente> pacientes = new ArrayList<>();

        try{
            log.info("Buscando pacientes cadastrados");

            connection = configuracaoJDBC.getConnection();

            st = connection.createStatement();

            ResultSet rs = st.executeQuery(sqlSelect);

            while(rs.next()){
                pacientes.add(criarPaciente(rs));
            }
        } catch (SQLException e){
            log.error("Erro ao buscar todos os pacientes: " + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            st.close();
        }
        return pacientes;
    }

    @Override
    public void alterar(Paciente paciente) throws SQLException {
        log.info("Abrindo conexão");

        String sqlUpdate = String.format("UPDATE paciente SET nome = '%s', sobrenome = '%s', endereco = '%s', rg = '%s', dataDeAlta = '%s' WHERE id = '%s'", paciente.getNome(), paciente.getSobrenome(), paciente.getEndereco(), paciente.getRg(), paciente.getDataDeAlta(), paciente.getId());

        try{
            log.info("Alterando o paciente " + paciente.getNome() + " " + paciente.getSobrenome());

            connection = configuracaoJDBC.getConnection();

            st = connection.createStatement();
            st.execute(sqlUpdate);
        } catch (SQLException e){
            log.error("Erro ao alterar paciente: " + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
    }

    @Override
    public Optional<Paciente> buscarPorId(int id) throws SQLException {
        log.info("Abrindo conexão");

        String sqlSelectWhere = String.format("SELECT * FROM paciente WHERE id = %s", id);

        Paciente paciente = null;

        try{
            log.info("Buscando paciente de id: " + id);

            connection = configuracaoJDBC.getConnection();

            st = connection.createStatement();

            ResultSet rs = st.executeQuery(sqlSelectWhere);

            while(rs.next()){
                paciente = criarPaciente(rs);
            }
        }catch (SQLException e){
            log.error("Erro ao buscar paciente de id: " + id + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            st.close();
        }
        // @TODO: handle null return
        if(paciente == null) { throw new RuntimeException("Paciente de id: " + id + " não encontrado");}
        return paciente != null ? Optional.of(paciente) : null;
    }

    @Override
    public void excluir(int id) throws SQLException {
        log.info("Abrindo conexão");

        String sqlDelete = String.format("DELETE FROM paciente WHERE id = %s", id);

        try{
            log.info("Excluindo paciente " + id);

            connection = configuracaoJDBC.getConnection();

            st = connection.createStatement();
            st.execute(sqlDelete);
        } catch (SQLException e){
            log.error("Erro ao excluir paciente: " + e.getMessage());
        } finally {
            log.info("Fechando conexão");
            connection.close();
        }
    }

    private Paciente criarPaciente(ResultSet rs) throws SQLException{
        Integer id = rs.getInt(1);
        String nome = rs.getString(2);
        String sobrenome = rs.getString(3);
        String endereco = rs.getString(4);
        String rg = rs.getString(5);
        Date dataDeAlta = rs.getDate(6);

        return new Paciente(id, nome, sobrenome, endereco, rg, dataDeAlta);
    }
}
