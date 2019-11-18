package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Repositorios.AtividadesRepositorio;
import Repositorios.ObjetivosRepositorio;
import Repositorios.PesquisadoresRepositorio;
import Repositorios.PesquisasRepositorio;
import Repositorios.ProblemasRepositorio;

public class Persistencia {
	
	
	//PESQUISAS
	
	public void salvarPesquisas(PesquisasRepositorio pesquisasRepositorio, String caminho) throws Exception {
		FileOutputStream fos = new FileOutputStream("PesquisasRepositorio.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(pesquisasRepositorio);
		oos.close();
	}
	
	public PesquisasRepositorio carregarPesquisas() throws Exception{
		FileInputStream fis = new FileInputStream("PesquisasRepositorio.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		PesquisasRepositorio pesquisasRepositorio= (PesquisasRepositorio) ois.readObject();
		ois.close();
		return pesquisasRepositorio;
	}
	
	
	//PESQUISADORES
	
	public void salvarpesquisadores(PesquisadoresRepositorio pesquisadoresRepositorio, String caminho) throws Exception {
		FileOutputStream fos = new FileOutputStream("PesquisadoresRepositorio.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(pesquisadoresRepositorio);
		oos.close();
	}
	
	public PesquisadoresRepositorio carregarpesquisadores() throws Exception{
		FileInputStream fis = new FileInputStream("PesquisadoresRepositorio.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		PesquisadoresRepositorio pesquisadoresRepositorio= (PesquisadoresRepositorio) ois.readObject();
		ois.close();
		return pesquisadoresRepositorio;
	}
	
	
	//PROBLEMAS
	
	public void salvarProblemas(ProblemasRepositorio problemasRepositorio, String caminho) throws Exception {
		FileOutputStream fos = new FileOutputStream("ProblemasRepositorio.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(problemasRepositorio);
		oos.close();
	}
	
	public ProblemasRepositorio carregarProblemas() throws Exception{
		FileInputStream fis = new FileInputStream("ProblemasRepositorio.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ProblemasRepositorio problemasRepositorio= (ProblemasRepositorio) ois.readObject();
		ois.close();
		return problemasRepositorio;
	}
	
	
	//OBJETIVOS
	
	public void salvarObjetivos(ObjetivosRepositorio objetivosRepositorio, String caminho) throws Exception {
		FileOutputStream fos = new FileOutputStream("ObjetivosRepositorio.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(objetivosRepositorio);
		oos.close();
	}
	
	public ObjetivosRepositorio carregarObjetivos() throws Exception{
		FileInputStream fis = new FileInputStream("ObjetivosRepositorio.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ObjetivosRepositorio objetivosRepositorio= (ObjetivosRepositorio) ois.readObject();
		ois.close();
		return objetivosRepositorio;
	}
	
	
	//ATIVIDADES
	
	public void salvarAtividades(AtividadesRepositorio atividadesRepositorio, String caminho) throws Exception {
		FileOutputStream fos = new FileOutputStream("AtividadesRepositorio.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(atividadesRepositorio);
		oos.close();
	}
	
	public AtividadesRepositorio carregarAtividades() throws Exception{
		FileInputStream fis = new FileInputStream("AtividadesRepositorio.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		AtividadesRepositorio atividadesRepositorio= (AtividadesRepositorio) ois.readObject();
		ois.close();
		return atividadesRepositorio;
	}
	
}
