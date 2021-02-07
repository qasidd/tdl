'use strict';

// Variables

const _tdlAccordion = document.querySelector("#tdlAccordionFlush");

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
            const getAssigneesFromTask = (assignees) => {
                let result = "";

                if (assignees.length > 0) {
                    result = "Assigned to: ";
                }

                for (let i = 0; i < assignees.length; i++) {
                    result = result + assignees[i].name;

                    if (i !== assignees.length - 1) {
                        result = result + ", "
                    }
                }

                return result;
            };

            _tdlAccordion.innerHTML = "";

            for (let i = 0; i < tasks.length; i++) {

                _tdlAccordion.innerHTML = _tdlAccordion.innerHTML +
                `<div class="accordion-item">
                <h2 class="accordion-header" id="flush-heading${i}">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#flush-collapse${i}" aria-expanded="false"
                        aria-controls="flush-collapse${i}">
                        <div class="d-flex flex-column text-start">
                            <span class="task-title">${tasks[i].title}</span>
                            <span class="assigned-to">${getAssigneesFromTask(tasks[i].assignees)}</span>
                        </div>
                    </button>
                </h2>
                <div id="flush-collapse${i}" class="accordion-collapse collapse"
                    aria-labelledby="flush-heading${i}" data-bs-parent="#tdlAccordionFlush">
                    <div class="accordion-body d-flex flex-column text-start">
                        <p class="date-time-stamp">Added: ${tasks[i].dateTimeSet}</p>
                        <div class="task-edit-buttons d-flex justify-content-between">
                            <button type="button" class="btn btn-outline-primary btn-sm"
                                data-bs-toggle="modal" data-bs-target="#editTaskModal">Edit
                                                            task</button>
                            <button type="button" class="btn btn-outline-primary btn-sm"
                                data-bs-toggle="modal" data-bs-target="#addAssigneeToTaskModal">Add
                                                            assignee</button>
                            <button type="button"
                                class="btn btn-outline-primary btn-sm">Completed?</button>
                            <button type="button" class="btn btn-danger">Delete</button>
                        </div>
                    </div>
                </div>
            </div>`;
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
        .then(model => { 
            console.log(model); 
            readAllTasks();
        })
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
        .then(model => { 
            console.log(model);
            populateAssignees(); 
        })
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
        .then(model => { 
            console.log(model)
            populateAssignees();
        })
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
        .then(model => { 
            console.log("Delete successful");
            populateAssignees(); 
        })
        .catch(err => console.error(`error ${err}`));
};

const refresh = () => {
    readAllTasks();
    populateAssignees();
};