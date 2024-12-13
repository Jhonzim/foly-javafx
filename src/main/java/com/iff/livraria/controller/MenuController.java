package com.iff.livraria.controller;

import com.iff.livraria.App;
import com.iff.livraria.controller.ImagemController;
import com.iff.livraria.model.Livro;
import com.iff.livraria.model.Usuario;
import com.iff.livraria.utils.exception.ImageError;
import com.iff.livraria.utils.exception.NoChooseImageError;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class MenuController implements Initializable {
    
    private ImagemController img;
    private ImagemController imgcontroller;

    @FXML
    private Button btn_home;
    private Button btn_criar;
    private Button btn_sagas;
    @FXML
    private AnchorPane home;
    private AnchorPane criar;
    private AnchorPane sagas;
    @FXML
    private Button btnCriar;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtAutor;
    private TextField txtLivros;
    @FXML
    private TextField txtDescricao;
    @FXML
    private AnchorPane livros;
    private Button btnLivros;
    private AnchorPane novasaga;
    @FXML
    private Button btnVoltar;
    @FXML
    private HBox Hbox;
    @FXML
    private ImageView Imgbox;
    @FXML
    private Button btnImg;
    @FXML
    private CheckBox txtcheckBox;
    private Button btnExibir;
    @FXML
    private TableView<Livro> tableView;
    @FXML
    private TableColumn<Livro, String> tableNome;
    @FXML
    private TableColumn<Livro, String> tableAutor;
    @FXML
    private TableColumn<Livro, Boolean> tableLido;
    private ObservableList<Livro> listaLivrosObservable;
    @FXML
    private AnchorPane exibir;
    private AnchorPane editar;
    private CheckBox checkboxLido;
    private Label lblNome;
    private Label lblAutor;
    private Label lblDescricao;
    @FXML
    private Button btn_livros;
    @FXML
    private Button btn_exibir;
    private Button btn_editar;
    private Button btnAlterar;
    @FXML
    private TextField txtNomeEdicao;
    @FXML
    private TextField txtAutorEdicao;
    @FXML
    private TextField txtDescricaoEdicao;
    @FXML
    private CheckBox txtcheckBoxEdicao;

    private AnchorPane fazendoAteraracao;
    private Button btnAlteraracao;
    @FXML
    private AnchorPane edicao;
    @FXML
    private Button btnEdicao;
    @FXML
    private HBox Hbox2;
    @FXML
    private Button btnEditarExcluir;
    @FXML
    private Button imgEdicao;
    @FXML
    private Button btnGrafico;
    @FXML
    private Button btnExcluirHome;
    @FXML
    private Label txtNome_Home;
    @FXML
    private Label txtEmail_Home;
    @FXML
    private Button btnEditarHome;
    @FXML
    private ImageView ImgboxEdicao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.carregarLivros();
        preencherdados();
    }    
    
    //Conecção das Telas
    @FXML
    private void swithForm(ActionEvent event) {
         if(event.getSource()== btn_home){
            home.setVisible(true);
            livros.setVisible(false);
            exibir.setVisible(false);
            edicao.setVisible(false);
        }if(event.getSource()== btn_livros){
            home.setVisible(false);
            livros.setVisible(true);
            exibir.setVisible(false);
            edicao.setVisible(false);
        }if(event.getSource()== btn_exibir){
            home.setVisible(false);
            livros.setVisible(false);
            exibir.setVisible(true);
            edicao.setVisible(false);
        }
    }

    
    //botões
    @FXML
    private void OnActionbtnCriar(ActionEvent event) throws Exception {
        String nome = txtNome.getText().trim();
        String autor = txtAutor.getText().trim();
        boolean lido = txtcheckBox.isSelected();
        String descricao = txtDescricao.getText().trim();
        if (nome.isEmpty() || autor.isEmpty()) {
          throw new IllegalArgumentException("Todos os campos devem ser preenchidos!!");
        }
        String imagem="";
        try{
            if(img == null){
                throw new NoChooseImageError("Selecione uma imagem para salvar");
            }

            //O caminho retornado pela função deve ser colocado no banco de dados para encontar a imagem
            imagem = img.salvarImagem(); 
            Livro livro = new Livro(nome,autor,descricao,lido,imagem);
            LivroController.incluir(livro, SecaoController.usr);
            SecaoController.buscar();
            this.carregarLivros();
            showInfoDialog();
            
        }catch(NoChooseImageError e){
            System.out.println(e.getMessage());
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            
        }
        
    }

    @FXML
    private void OnactionbtnVoltar(ActionEvent event) throws IOException {
        App.setRoot("view/Home");
    }

    @FXML
    private void OnActionbtnImg(ActionEvent event) {
        try{
            FileChooser fs = new FileChooser();
            File file =  fs.showOpenDialog(null);

            if (!file.exists()) {
                throw new FileNotFoundException("O arquivo não foi encontrado ou não existe");
            }
            
            img = new ImagemController(file);
            
            
            Imgbox.setImage( img.converterFileParaJFXImage() );
            
            Imgbox.setFitWidth(150);
            Imgbox.setFitHeight(300);
            Imgbox.setPreserveRatio(true);
        
        }catch(FileNotFoundException e){
            e.printStackTrace();
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }catch(ImageError e){
            System.out.println(e.getMessage());;
        }
    }
    
    @FXML
    private void OnMouseClick(MouseEvent event) {
        if (event.getClickCount() == 2) { // Detecta clique duplo
            Livro livro = tableView.getSelectionModel().getSelectedItem();
            if (livro != null) {
                    // Preenche os campos de edição com os dados do livro
                    txtNomeEdicao.setText(livro.getNome());
                    txtAutorEdicao.setText(livro.getAutor());
                    txtDescricaoEdicao.setText(livro.getDescricao());
                    txtcheckBoxEdicao.setSelected(livro.isFoiLido());
                    
                    File fl = new File(livro.getImagem());

                    try{
                        
                        imgcontroller = new ImagemController(fl);
                        ImgboxEdicao.setImage(imgcontroller.converterFileParaJFXImage());
                    } catch (ImageError ex) 
                    {
                    ImgboxEdicao.setImage(null);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("O arquivo não é uma imagem!");
                    alert.showAndWait();
                    
                    }catch (Exception ex) 
                    {
                        ImgboxEdicao.setImage(null);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setHeaderText("O arquivo não existe!");
                        alert.showAndWait();
                    }
                    
                    // Torna a tela de edição visível
                    exibir.setVisible(false);
                    edicao.setVisible(true);
            }
        }
    }
    @FXML
    private void OnActionSalvarEdicao(ActionEvent event) throws Exception {
    Livro livro = tableView.getSelectionModel().getSelectedItem();
    if (livro != null) {
        // Atualiza o objeto com os novos valores
        livro.setNome(txtNomeEdicao.getText().trim());
        livro.setAutor(txtAutorEdicao.getText().trim());
        livro.setDescricao(txtDescricaoEdicao.getText().trim());
        livro.setFoiLido(txtcheckBoxEdicao.isSelected());
        ImagemController.apagarImagem(livro.getImagem());
        livro.setImagem(imgcontroller.salvarImagem());

        // Atualiza a tabela para refletir as mudanças
        LivroController.editar(livro);
        SecaoController.buscar();
        tableView.refresh();

        // Voltar à tela principal (opcional)
        if(event.getSource()== btnEdicao){
            home.setVisible(false);
            livros.setVisible(false);
            exibir.setVisible(true);
            edicao.setVisible(false);
        }
    }
 }

    @FXML
    private void OnActionbtnEditarExcluir(ActionEvent event){
       Livro livro = tableView.getSelectionModel().getSelectedItem();
        try {
            LivroController.excluir(livro);
            SecaoController.buscar();
            this.carregarLivros();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Livro excluido com sucesso!");
            alert.showAndWait(); 
            exibir.setVisible(true);
            edicao.setVisible(false);
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Atenção Deu Erro!");
            alert.showAndWait(); 
        }
    }

    @FXML
    private void OnActionbtnGrafico(ActionEvent event) throws IOException {
        App.setRoot("view/Grafico");
    }

    @FXML
    private void OnActionbtnExcluirHome(ActionEvent event) {
        try {
        boolean excluir=false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Change");
        alert.setHeaderText("A change occurred.");
        alert.setContentText("Do you really want to change the value?");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.isPresent() && buttonType.get().equals(ButtonType.OK)) {
            excluir = true;
        }
        if(excluir){
            UsuarioController.delete(SecaoController.usr);
            SecaoController.usr = null;
            App.setRoot("view/Home");
        }
            
        } catch (Exception ex) {
            System.out.println("Tem certeza que deseja apagar este usuario?? --- Colocar");
        }
    }

    @FXML
    private void OnActionbtnEditarHome(ActionEvent event) {
    }

    @FXML
    private void OnActionbtnImgEdicao(ActionEvent event) {
        try{
            FileChooser fs = new FileChooser();
            File file =  fs.showOpenDialog(null);

            if (!file.exists()) {
                throw new FileNotFoundException("O arquivo não foi encontrado ou não existe");
            }
            
            imgcontroller = new ImagemController(file);
            
            
            ImgboxEdicao.setImage( imgcontroller.converterFileParaJFXImage() );
            
            ImgboxEdicao.setFitWidth(150);
            ImgboxEdicao.setFitHeight(300);
            ImgboxEdicao.setPreserveRatio(true);
        
        }catch(FileNotFoundException e){
            e.printStackTrace();
            
        }
        catch(Exception e){
            e.printStackTrace();
            
        }catch(ImageError e){
            System.out.println(e.getMessage());;
        }
    }
    
    //Funções
    
    private void preencherdados(){
        Usuario usr = SecaoController.usr; // Use um objeto existente se possível.
        if (usr != null) {
        txtNome_Home.setText(usr.getNome());
        txtEmail_Home.setText(usr.getNomeDeUsuario());
        } else {
        System.out.println("Erro: Usuário não está inicializado.");
        }

    }
    
    // Exibe o diálogo para confirmar que o livro foi salvo
    public static void showInfoDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Atenção!");
        alert.setHeaderText("Livro salvo com sucesso!");
        alert.showAndWait(); 
    }
    
    public void carregarLivros(){
        try{
            tableNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tableAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            tableLido.setCellValueFactory(cellData -> {
                return new SimpleBooleanProperty(cellData.getValue().isFoiLido());
            });

            tableLido.setCellFactory(col -> {
                CheckBoxTableCell<Livro, Boolean> cell = new CheckBoxTableCell<>();
                cell.setOnMouseClicked(event -> {
                    if (cell.isSelected()) {
                        cell.getTableRow().getItem().setFoiLido(true);
                    } else {
                        cell.getTableRow().getItem().setFoiLido(false);
                    }
                });
                return cell;
            });
            listaLivrosObservable = FXCollections.observableArrayList(SecaoController.livros);
            tableView.setItems(listaLivrosObservable);
            editar.setVisible(true);
            System.out.println(SecaoController.livros.size());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}