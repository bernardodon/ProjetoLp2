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

/**
 * Classe responsavel por manipular todas as acoes de permanencia de estado do sistema.
 * @author Bernard Dantas Odon
 *
 */
public class Persistencia {
	
	
	/**
	 * Salva o respositorio de pesquisas em um arquivo .txt
	 * @param pesquisasRepositorio Repositorio de pesquisas que sera salvo no arquivo.
	 * @throws Exception
	 */
	public void salvarPesquisas(PesquisasRepositorio pesquisasRepositorio) throws Exception {
		FileOutputStream fos = new FileOutputStream("PesquisasRepositorio.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(pesquisasRepositorio);
		oos.close();
	}
	
	/**
	 * Recupera os repositorios de pesquisa de um arquivo .txt
	 * @return Retorna o repositorio de pesquisas.
	 * @throws Exception
	 */
	public PesquisasRepositorio carregarPesquisas() throws Exception{
		FileInputStream fis = new FileInputStream("PesquisasRepositorio.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		PesquisasRepositorio pesquisasRepositorio= (PesquisasRepositorio) ois.readObject();
		ois.close();
		return pesquisasRepositorio;
	}
	
	
	/**
	 * Salva o respositorio de pesquisadores em um arquivo .txt
	 * @param pesquisadoresRepositorio Repositorio de pesquisadores que sera salvo no arquivo.
	 * @throws Exception
	 */
	public void salvarpesquisadores(PesquisadoresRepositorio pesquisadoresRepositorio) throws Exception {
		FileOutputStream fos = new FileOutputStream("PesquisadoresRepositorio.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(pesquisadoresRepositorio);
		oos.close();
	}
	/**
	 * Recupera os repositorios de pesquisadores de um arquivo .txt
	 * @return Retorna o repositorio de pesquisadores.
	 * @throws Exception
	 */
	public PesquisadoresRepositorio carregarpesquisadores() throws Exception{
		FileInputStream fis = new FileInputStream("PesquisadoresRepositorio.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		PesquisadoresRepositorio pesquisadoresRepositorio= (PesquisadoresRepositorio) ois.readObject();
		ois.close();
		return pesquisadoresRepositorio;
	}
	
	/**
	 * Salva o respositorio de problemas em um arquivo .txt
	 * @param problemasRepositorio Repositorio de problemas que sera salvo no arquivo.
	 * @throws Exception
	 */
	public void salvarProblemas(ProblemasRepositorio problemasRepositorio) throws Exception {
		FileOutputStream fos = new FileOutputStream("ProblemasRepositorio.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(problemasRepositorio);
		oos.close();
	}
	/**
	 * Recupera os repositorios de problemas de um arquivo .txt
	 * @return Retorna o repositorio de problemas.
	 * @throws Exception
	 */
	public ProblemasRepositorio carregarProblemas() throws Exception{
		FileInputStream fis = new FileInputStream("ProblemasRepositorio.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ProblemasRepositorio problemasRepositorio= (ProblemasRepositorio) ois.readObject();
		ois.close();
		return problemasRepositorio;
	}
	
	/**
	 * Salva o respositorio de objetivos em um arquivo .txt
	 * @param objetivosRepositorio Repositorio de objetivos que sera salvo no arquivo.
	 * @throws Exception
	 */
	public void salvarObjetivos(ObjetivosRepositorio objetivosRepositorio) throws Exception {
		FileOutputStream fos = new FileOutputStream("ObjetivosRepositorio.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(objetivosRepositorio);
		oos.close();
	}
	/**
	 * Recupera os repositorios de objetivos de um arquivo .txt
	 * @return Retorna o repositorio de objetivos.
	 * @throws Exception
	 */
	public ObjetivosRepositorio carregarObjetivos() throws Exception{
		FileInputStream fis = new FileInputStream("ObjetivosRepositorio.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		ObjetivosRepositorio objetivosRepositorio= (ObjetivosRepositorio) ois.readObject();
		ois.close();
		return objetivosRepositorio;
	}
	
	
	/**
	 * Salva o respositorio de atividades em um arquivo .txt
	 * @param atividadesRepositorio Repositorio de atividades que sera salvo no arquivo.
	 * @throws Exception
	 */
	public void salvarAtividades(AtividadesRepositorio atividadesRepositorio) throws Exception {
		FileOutputStream fos = new FileOutputStream("AtividadesRepositorio.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(atividadesRepositorio);
		oos.close();
	}
	/**
	 * Recupera os repositorios de objetivos de um arquivo .txt
	 * @return Retorna o repositorio de objetivos.
	 * @throws Exception
	 */
	public AtividadesRepositorio carregarAtividades() throws Exception{
		FileInputStream fis = new FileInputStream("AtividadesRepositorio.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		AtividadesRepositorio atividadesRepositorio= (AtividadesRepositorio) ois.readObject();
		ois.close();
		return atividadesRepositorio;
	}
	
}
