const API_DIVIDAS = "http://localhost:8080/dividas";
const API_TRANSACOES = "http://localhost:8080/transacoes";

function mostrar(tipo) {
  if (tipo === "dividas") {
    carregarDividas();
  } else {
    carregarTransacoes();
  }
}

// ----- DIVIDAS -----
function carregarDividas() {
  fetch(`${API_DIVIDAS}/valor-total`)
    .then(res => res.json())
    .then(total => {
      fetch(API_DIVIDAS)
        .then(res => res.json())
        .then(dividas => {
          document.getElementById("conteudo").innerHTML = `
            <h2>Dívidas</h2>
            <div class="card-total">Valor Total: R$${total}</div>
            <input id="descricaoDivida" placeholder="Descrição">
            <input id="valorDivida" type="number" placeholder="Valor">
            <input id="dataVencimento" type="date">
            <button onclick="adicionarDivida()">Adicionar</button>

            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Descrição</th>
                  <th>Valor</th>
                  <th>Data Vencimento</th>
                  <th>Ações</th>
                </tr>
              </thead>
              <tbody>
                ${dividas.map(d => `
                  <tr>
                    <td>${d.id}</td>
                    <td>${d.descricao}</td>
                    <td>R$${d.valor}</td>
                    <td>${d.dataVencimento}</td>
                    <td>
                      <button onclick="deletarDivida(${d.id})">Deletar</button>
                      <button onclick="editarDivida(${d.id})">Editar</button>
                    </td>
                  </tr>`).join("")}
              </tbody>
            </table>
          `;
        });
    });
}

function adicionarDivida() {
  const descricao = document.getElementById("descricaoDivida").value;
  const valor = parseFloat(document.getElementById("valorDivida").value);
  const dataVencimento = document.getElementById("dataVencimento").value;

  fetch(API_DIVIDAS, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ descricao, valor, dataVencimento })
  }).then(() => carregarDividas());
}

function deletarDivida(id) {
  fetch(`${API_DIVIDAS}/${id}`, { method: "DELETE" })
    .then(() => carregarDividas());
}

function editarDivida(id) {
  fetch(`${API_DIVIDAS}/${id}`)
    .then(res => res.json())
    .then(d => {
      console.log("Objeto recebido do back:", d);

      let dataFormatada = d.dataVencimento;
      if (dataFormatada && dataFormatada.includes("T")) {
        dataFormatada = dataFormatada.split("T")[0];
      }

      document.getElementById("conteudo").innerHTML += `
        <div>
          <h3>Editar Dívida</h3>
          <input id="editDescricaoDivida" value="${d.descricao || ""}">
          <input id="editValorDivida" type="number" value="${d.valor || 0}">
          <input id="editDataVencimento" type="date" value="${dataFormatada || ""}">
          <button onclick="atualizarDivida(${id})">Salvar</button>
        </div>
      `;
    });
}

function atualizarDivida(id) {
  const descricao = document.getElementById("editDescricaoDivida").value;
  const valor = parseFloat(document.getElementById("editValorDivida").value);
  let dataVencimento = document.getElementById("editDataVencimento").value;

  const payload = { id, descricao, valor, dataVencimento };
  console.log("Payload Dívida:", payload);

  fetch(`${API_DIVIDAS}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  })
  .then(res => {
    console.log("Status resposta Dívida:", res.status);
    if (!res.ok) throw new Error("Erro ao atualizar dívida");
    return res.json();
  })
  .then(() => carregarDividas())
  .catch(err => console.error("Erro no PUT Dívida:", err));
}

// ----- TRANSACOES -----
function carregarTransacoes() {
  fetch(`${API_TRANSACOES}/valor-total`)
    .then(res => res.json())
    .then(total => {
      fetch(API_TRANSACOES)
        .then(res => res.json())
        .then(transacoes => {
          document.getElementById("conteudo").innerHTML = `
            <h2>Transações</h2>
            <div class="card-total">Valor Total: R$${total}</div>
            <input id="descricaoTransacao" placeholder="Descrição">
            <input id="valorTransacao" type="number" placeholder="Valor">
            <input id="dataTransacao" type="date">
            <button onclick="adicionarTransacao()">Adicionar</button>

            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Descrição</th>
                  <th>Valor</th>
                  <th>Data</th>
                  <th>Ações</th>
                </tr>
              </thead>
              <tbody>
                ${transacoes.map(t => `
                  <tr>
                    <td>${t.id}</td>
                    <td>${t.descricao}</td>
                    <td>R$${t.valor}</td>
                    <td>${t.data}</td>
                    <td>
                      <button onclick="deletarTransacao(${t.id})">Deletar</button>
                      <button onclick="editarTransacao(${t.id})">Editar</button>
                    </td>
                  </tr>`).join("")}
              </tbody>
            </table>
          `;
        });
    });
}

function adicionarTransacao() {
  const descricao = document.getElementById("descricaoTransacao").value;
  const valor = parseFloat(document.getElementById("valorTransacao").value);
  const data = document.getElementById("dataTransacao").value;

  fetch(API_TRANSACOES, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ descricao, valor, data })
  }).then(() => carregarTransacoes());
}

function deletarTransacao(id) {
  fetch(`${API_TRANSACOES}/${id}`, { method: "DELETE" })
    .then(() => carregarTransacoes());
}

function editarTransacao(id) {
  fetch(`${API_TRANSACOES}/${id}`)
    .then(res => res.json())
    .then(t => {
      console.log("Objeto recebido do back:", t);

      let dataFormatada = t.data;
      if (dataFormatada && dataFormatada.includes("T")) {
        dataFormatada = dataFormatada.split("T")[0];
      }

      document.getElementById("conteudo").innerHTML += `
        <div>
          <h3>Editar Transação</h3>
          <input id="editDescricaoTransacao" value="${t.descricao || ""}">
          <input id="editValorTransacao" type="number" value="${t.valor || 0}">
          <input id="editDataTransacao" type="date" value="${dataFormatada || ""}">
          <button onclick="atualizarTransacao(${id})">Salvar</button>
        </div>
      `;
    });
}

function atualizarTransacao(id) {
  const descricao = document.getElementById("editDescricaoTransacao").value;
  const valor = parseFloat(document.getElementById("editValorTransacao").value);
  let data = document.getElementById("editDataTransacao").value;

  const payload = { id, descricao, valor, data };
  console.log("Payload Transação:", payload);

  fetch(`${API_TRANSACOES}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  })
  .then(res => {
    console.log("Status resposta Transação:", res.status);
    if (!res.ok) throw new Error("Erro ao atualizar transação");
    return res.json();
  })
  .then(() => carregarTransacoes())
  .catch(err => console.error("Erro no PUT Transação:", err));
}
