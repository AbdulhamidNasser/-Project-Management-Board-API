const baseURL = "http://localhost:8080/api"; // Replace this with your backend API URL

// Board Functions

async function createBoard(title) {
  try {
    const response = await fetch(`${baseURL}/boards`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ title }),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error creating board:", error);
  }
}

async function getAllBoards() {
  try {
    const response = await fetch(`${baseURL}/boards`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching boards:", error);
  }
}

async function updateBoard(boardId, updatedBoard) {
  try {
    const response = await fetch(`${baseURL}/boards/${boardId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedBoard),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error updating board:", error);
  }
}

async function deleteBoard(boardId) {
  try {
    const response = await fetch(`${baseURL}/boards/${boardId}`, {
      method: "DELETE",
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error deleting board:", error);
  }
}

// Card Functions

async function createCard(boardId, card) {
  try {
    const response = await fetch(`${baseURL}/boards/${boardId}/cards`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(card),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error creating card:", error);
  }
}

async function getAllCards(boardId) {
  try {
    const response = await fetch(`${baseURL}/boards/${boardId}/cards`);
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error fetching cards:", error);
  }
}

async function updateCard(boardId, cardId, updatedCard) {
  try {
    const response = await fetch(`${baseURL}/boards/${boardId}/cards/${cardId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(updatedCard),
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error updating card:", error);
  }
}

async function deleteCard(boardId, cardId) {
  try {
    const response = await fetch(`${baseURL}/boards/${boardId}/cards/${cardId}`, {
      method: "DELETE",
    });
    const data = await response.json();
    return data;
  } catch (error) {
    console.error("Error deleting card:", error);
  }
}

// Add event listeners to interact with the backend

// Board event listeners

const createBoardButton = document.getElementById("createBoardButton");
createBoardButton.addEventListener("click", async () => {
  const title = document.getElementById("boardTitleInput").value;
  const newBoard = await createBoard(title);
  console.log("Created board:", newBoard);
});

const getAllBoardsButton = document.getElementById("getAllBoardsButton");
const boardList = document.getElementById("boardList");

getAllBoardsButton.addEventListener("click", async () => {
  const boards = await getAllBoards();
  console.log("All boards:", boards);

  boardList.innerHTML = ""; // Clear the previous board list

  boards.forEach(board => {
    const boardElement = document.createElement("div");
    boardElement.innerHTML = `<h3>Board ID: ${board.id}</h3><p>Board Name: ${board.name}</p>`;
    boardList.appendChild(boardElement);
  });
});

// Card event listeners

const createCardButton = document.getElementById("createCardButton");
createCardButton.addEventListener("click", async () => {
  const boardId = document.getElementById("boardIdForCard").value;
  const card = {
    title: "New Card Title",
    description: "New Card Description",
    section: 1, // Replace with the desired section number (1, 2, or 3)
  };
  const newCard = await createCard(boardId, card);
  console.log("Created card:", newCard);
});

const getAllCardsButton = document.getElementById("getAllCardsButton");
getAllCardsButton.addEventListener("click", async () => {
  const boardId = document.getElementById("boardIdForCard").value;
  const cards = await getAllCards(boardId);
  console.log("All cards:", cards);
});

const updateCardButton = document.getElementById("updateCardButton");
updateCardButton.addEventListener("click", async () => {
  const boardId = document.getElementById("boardIdForCard").value;
  const cardId = document.getElementById("cardIdToUpdate").value;
  const updatedCard = {
    title: "Updated Card Title",
    description: "Updated Card Description",
    section: 2, // Replace with the desired section number (1, 2, or 3)
  };
  const result = await updateCard(boardId, cardId, updatedCard);
  console.log("Update result:", result);
});

const deleteCardButton = document.getElementById("deleteCardButton");
deleteCardButton.addEventListener("click", async () => {
  const boardId = document.getElementById("boardIdForCard").value;
  const cardId = document.getElementById("cardIdToDelete").value;
  const result = await deleteCard(boardId, cardId);
  console.log("Delete result:", result);
});
