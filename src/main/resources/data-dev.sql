INSERT INTO task_domain(TITLE, COMPLETED, DATE_TIME_SET)
VALUES
('Do laundry', FALSE, NOW()),
('Make coffee', FALSE, NOW()),
('Take out bins', TRUE, '2021-02-04 19:00'),
('Buy masks', FALSE, '2021-02-01 12:00');

INSERT INTO assignee_domain(NAME)
VALUES
('Jane'),
('Bob'),
('Paul'),
('Sally');

INSERT INTO tasks_assignees(TASK_ID, ASSIGNEE_ID)
VALUES
(1, 1),
(2, 2),
(2, 3),
(4, 2);