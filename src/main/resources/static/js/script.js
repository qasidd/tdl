'use strict';

// Variables

const _newTaskTitle = document.querySelector("#newTaskTitle");

const _newAssigneeName = document.querySelector("#newAssigneeName");
const _editAssigneeName = document.querySelector("#editAssigneeName");
const _addAssigneeToTaskSelect = document.querySelector("#addAssigneeToTaskSelect");
const _editAssigneeSelect = document.querySelector("#editAssigneeSelect");


// Task


const readAllTasks = () => {
    fetch("http://localhost:8080/task/read/all")
        .then(response => response.json())
        .then(tasks => {
            for (let t of tasks) {
                console.log(t);
            }
        })
        .catch(err => console.error(`error ${err}`));
};

const createTask = () => {
    const newTaskTitle = _newTaskTitle.value;

    let data = {
        "title": newTaskTitle,
        "completed": false
    }

    fetch("http://localhost:8080/task/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(model => console.log(model))
        .catch(err => console.error(`error ${err}`));
};


// Assignee


const populateAssignees = () => {
    _addAssigneeToTaskSelect.innerHTML = '';
    _editAssigneeSelect.innerHTML = '';

    fetch("http://localhost:8080/assignee/read/all")
        .then(response => response.json())
        .then(assignees => {
            for (let i = 0; i < assignees.length; i++) {
                _addAssigneeToTaskSelect.innerHTML = `${_addAssigneeToTaskSelect.innerHTML} <option value="${assignees[i].id}">${assignees[i].name}</option>`;
                _editAssigneeSelect.innerHTML = `${_editAssigneeSelect.innerHTML} <option value="${assignees[i].id}">${assignees[i].name}</option>`;
            }
        })
        .catch(err => console.error(`error ${err}`));
};

const createAssignee = () => {
    const newAssigneeName = _newAssigneeName.value;

    let data = {
        "name": newAssigneeName
    }

    fetch("http://localhost:8080/assignee/create", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(model => console.log(model))
        .catch(err => console.error(`error ${err}`));
};

const updateAssignee = () => {
    const editAssigneeId = _editAssigneeSelect.value;
    const editAssigneeName = _editAssigneeName.value;

    let data = {
        "name": editAssigneeName
    }

    fetch(`http://localhost:8080/assignee/update?id=${editAssigneeId}`, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(model => console.log(model))
        .catch(err => console.error(`error ${err}`));
};

const deleteAssignee = () => {
    const editAssigneeId = _editAssigneeSelect.value;

    fetch(`http://localhost:8080/assignee/delete/${editAssigneeId}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => response.json())
        .then(model => console.log("Delete successful"))
        .catch(err => console.error(`error ${err}`));
};

const refresh = () => {
    readAllTasks();
    populateAssignees();
};