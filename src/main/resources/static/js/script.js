console.log("testando conexão com JS file")

document.getElementById("excluir-btn").addEventListener("click", function() {
    if (confirm("Tem certeza que deseja EXCLUIR PERMANENTEMENTE este registro?")) {
        document.getElementById("excluir-form").submit();
    }
});
