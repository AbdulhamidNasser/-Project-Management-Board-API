let hostname = window.location.hostname;
const apiUrl = 'http://'+hostname+':8080/api';

var chosenBoardId=1
// Function to create a new board
async function createBoard(boardTitle) {
  try {
    const response = await fetch(`${apiUrl}/boards`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        title: boardTitle,
      }),
    });

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error creating board:', error);
    return null;
  }
}


// Function to display boards in the UI
function displayBoards(boards) {
  const boardListContainer = document.getElementById('boardList');
  boardListContainer.innerHTML = ''; // Clear the container before rendering

  boards.forEach(board => {
 
    const boardElement = document.createElement('div');
    boardElement.innerHTML = `<h3>${board.name}</h3>`;
    boardListContainer.appendChild(boardElement);
    boardElement.addEventListener("click", changeBoardId);
    function changeBoardId(){  chosenBoardId=board.id;
      displayCardsBySection()
    }

  });
}




// Function to get all boards
async function getAllBoards() {
  try {
    const response = await fetch(`${apiUrl}/boards`);
    const data = await response.json();
    displayBoards(data); // Call the displayBoards function with the retrieved boards
    return data;
  } catch (error) {
    console.error('Error getting all boards:', error);
    return null;
  }
}









// Function to create a new card in a board
async function createCard( cardTitle, cardDescription, cardStatus) {
  try {
    const response = await fetch(`${apiUrl}/boards/${chosenBoardId}/cards`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        title: cardTitle,
        description: cardDescription,
        section: cardStatus,
      }),
    });

    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error creating card:', error);
    return null;
  }
}

// Function to update a card in a board
async function updateCard( cardId, updatedCardTitle, updatedCardDescription, updatedCardStatus) {
  try {
    const response = await fetch(`${apiUrl}/boards/${chosenBoardId}/cards/${cardId}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        title: updatedCardTitle,
        description: updatedCardDescription,
        section: updatedCardStatus,
      }),
    });

    const data = await response.json();
    displayCardsBySection();
    return data;
  } catch (error) {
    console.error('Error updating card:', error);
    return null;
  }
}

// Function to delete a card from a board
async function deleteCard( cardId) {
  try {
    const response = await fetch(`${apiUrl}/boards/${chosenBoardId}/cards/${document.getElementById('selectId').value}`, {
      method: 'DELETE',
    });

    const data = await response.json();
    displayCardsBySection();
    return data;
  } catch (error) {
    console.error('Error deleting card:', error);
    return null;
  }
}



// Create a new board when the "Create Board" button is clicked
document.getElementById('createBoardButton').addEventListener('click', async () => {
  const boardTitle = document.getElementById('boardTitleInput').value;
  const newBoard = await createBoard(boardTitle);
  if (newBoard) {
    // Handle successful board creation (e.g., display the new board)
    console.log('New board created:', newBoard);
  } else {
    // Handle error (e.g., show an error message)
    console.log('Failed to create a new board.');
  }
});

// Get all boards when the "Get All Boards" button is clicked
document.getElementById('getAllBoardsButton').addEventListener('click', async () => {
  const boards = await getAllBoards();
  if (boards) {
    // Handle successful retrieval of all boards (e.g., display the list of boards)
    console.log('All boards:', boards);
  } else {
    // Handle error (e.g., show an error message)
    console.log('Failed to get all boards.');
  }
});


// Create a new card when the "Add Card" button is clicked
document.getElementById('addCardButton').addEventListener('click', async () => {
  const cardTitle = document.getElementById('cardName').value;
  const cardDescription = document.getElementById('cardDescription').value;
  const cardStatus = parseInt(document.getElementById('cardStatusInput').value); // Parse the value as an integer
  const newCard = await createCard( cardTitle, cardDescription, cardStatus);
  if (newCard) {
    // Handle successful card creation (e.g., display the new card)
    console.log('New card created:', newCard);
    displayCardsBySection();
  } else {
    // Handle error (e.g., show an error message)
    console.log('Failed to create a new card.');
  }
});



// Update a card when the "Update Card" button is clicked
document.getElementById('updateCardButton').addEventListener('click', async () => {
  const cardId = parseInt(document.getElementById('updateCardIdInput').value);
  const updatedCardTitle = document.getElementById('updatedCardName').value;
  const updatedCardDescription = document.getElementById('updatedCardDescription').value;
  const updatedCardStatus = parseInt(document.getElementById('updateCardStatusInput').value);

  const updatedCard = await updateCard( cardId, updatedCardTitle, updatedCardDescription, updatedCardStatus);
  if (updatedCard) {
    // Handle successful card update (e.g., display the updated card)
    console.log('Card updated:', updatedCard);
  } else {
    // Handle error (e.g., show an error message)
    console.log('Failed to update the card.');
  }
});

async function displayCardsBySection() {
  const boardId = chosenBoardId; // Assuming the card should be added to the board with ID 1
  try {
    // Fetch the list of cards for the board
    const response = await fetch(`http://localhost:8080/api/boards/${chosenBoardId}/cards`);
    if (!response.ok) {
      throw new Error(`Failed to fetch cards. Status: ${response.status} ${response.statusText}`);
    }
    // Parse the response body as JSON
    const cards = await response.json();
    // Get the containers for each section
    const todoContainer = document.getElementById('todo');
    todoContainer.innerHTML = '<h2>To Do</h2>'; // Reset the container with the section title
    const inProgressContainer = document.getElementById('inprogress');
    inProgressContainer.innerHTML = '<h2>In Progress</h2>'; // Reset the container with the section title
    const doneContainer = document.getElementById('done');
    doneContainer.innerHTML = '<h2>Done</h2>'; // Reset the container with the section title
    // Loop through the cards and create HTML elements to display them
    const deleteIdList=document.getElementById('selectId')
    deleteIdList.innerHTML="";
    cards.forEach((card, index) => {

const deleteOption=document.createElement('option');
deleteOption.value=card.cardId;
deleteOption.innerText="Card Id :"+card.cardId
deleteIdList.appendChild(deleteOption)
      const cardElement = document.createElement('div');
      cardElement.classList.add('card'); // Add the 'card' class for styling
      cardElement.innerHTML = `
        <p>Card ID: ${card.cardId}</p>
        <p>Title: ${card.title}</p>
        <p>Description: ${card.description}</p>
      `;
      // Add the card to the corresponding container based on the section
      if (card.section === 1) {
        todoContainer.appendChild(cardElement);
      } else if (card.section === 2) {
        inProgressContainer.appendChild(cardElement);
      } else if (card.section === 3) {
        doneContainer.appendChild(cardElement);
      }
    });
  } catch (error) {
    console.error('Error fetching cards:', error);
    alert('An error occurred while fetching cards.');
  }
}
// Initial call to fetch and display cards when the page loads
displayCardsBySection();

// Delete a card when the "Delete Card" button is clicked
document.getElementById('deleteCardButton').addEventListener('click', async () => {


  const deletionResult = await deleteCard();
  if (deletionResult && deletionResult.successful) {
    // Handle successful card deletion (e.g., remove the card from the display)
    console.log('Card deleted:', deletionResult);
  } else {
    // Handle error (e.g., show an error message)
    console.log('Failed to delete the card.');
  }
});