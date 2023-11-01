package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PedidoDAO;
import dao.PessoaDAO;
import dao.ProdutoDAO;
import model.Pedido;
import model.Pessoa;
import model.Produto;



/**
 * Servlet implementation class PedidoController
 */
@WebServlet("/PedidoController")
public class PedidoController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private PedidoDAO pedidoDAO;
	private static final String MANTER_PEDIDO = "manterPedido.jsp";
	private static final String LISTAR_PEDIDOS = "listarPedidos.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PedidoController() {
		super();
		pedidoDAO = new PedidoDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String avancar = "";

		if (acao.equalsIgnoreCase("criar")) {
			avancar = MANTER_PEDIDO;
		}

		else if (acao.equalsIgnoreCase("buscarPorId")) {
			int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
			Pedido pedido = pedidoDAO.findbyIdPedido(id_pedido);

			List<Pedido> listaPedido = new ArrayList<>();
			listaPedido.add(pedido);
			request.setAttribute("listaPedidos", listaPedido);

			avancar = LISTAR_PEDIDOS;

		} else if (acao.equalsIgnoreCase("atualizar")) {

			String id_pedido = request.getParameter("id_pedido");
			String data = request.getParameter("data");
			String id_pessoa = request.getParameter("id_pessoa");

			Pedido pedido = new Pedido();
			Pessoa pessoa = new Pessoa();
			PessoaDAO pessoaDAO = new PessoaDAO();
			
			pedido.setId(Integer.parseInt(id_pedido));
			pedido.setDataPedido(Date.valueOf(data));
			pedido.setPessoa(pessoa);

			request.setAttribute("pedido", pedido);

			avancar = MANTER_PEDIDO;

		} else if (acao.equalsIgnoreCase("remover")) {

			int id_pedido = Integer.parseInt(request.getParameter("id_pedido"));
			Pedido pedido = pedidoDAO.findbyIdPedido(id_pedido);
			pedidoDAO.deleteById(pedido);

			List<Pedido> listaPedidos = pedidoDAO.findByAll();
			request.setAttribute("listaPedidos", listaPedidos);

			avancar = LISTAR_PEDIDOS;

		} else if (acao.equalsIgnoreCase("listarTodos")) {

			List<Pedido> listaPedidos = pedidoDAO.findByAll();
			request.setAttribute("listaPedidos", listaPedidos);

			avancar = LISTAR_PEDIDOS;
		}

		else {
			avancar = LISTAR_PEDIDOS;
		}

		RequestDispatcher pagina = request.getRequestDispatcher(avancar);
		pagina.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id_pedido = request.getParameter("id_pedido");
		String data = request.getParameter("nome");
		String id_pessoa = request.getParameter("id_pessoa");

		// Se o id_pessoa e vazio, criamos. Senao, atualizamos uma pessoa existente
		if (id_pedido.isEmpty()) {

			Pedido pedido = new Pedido();
			pedido.setDataPedido(data);


			produtoDAO.addProduto(produto);

			request.setAttribute("listaProdutos", produtoDAO.findAllProduto());

			RequestDispatcher pagina = request.getRequestDispatcher(LISTAR_PRODUTOS);
			pagina.forward(request, response);
			
		} else {
			
			Produto produto = new Produto();
			produto.setId(Integer.parseInt(id_produto));
			produto.setNome(nome);

			produtoDAO.updateNameByIdProduto(produto);

			request.setAttribute("listaProdutos", produtoDAO.findAllProduto());

			RequestDispatcher pagina = request.getRequestDispatcher(LISTAR_PRODUTOS);
			pagina.forward(request, response);
		}

	}

}

