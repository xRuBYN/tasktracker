import React, { useEffect, useState } from 'react';
import { Container, Row, Col, Dropdown, DropdownToggle, DropdownMenu, DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEllipsisV, faTrash } from '@fortawesome/free-solid-svg-icons';
import './board.scss';

const Board = () => {
  const [toDoItems, setToDoItems] = useState([]);
  const [doneItems, setDoneItems] = useState([]);
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const [selectedItemIndex, setSelectedItemIndex] = useState(null);

  const [dropdownOpenToDo, setDropdownOpenToDo] = useState(false);
  const [dropdownOpenDone, setDropdownOpenDone] = useState(false);
  const [selectedItemIndexToDo, setSelectedItemIndexToDo] = useState(null);
  const [selectedItemIndexDone, setSelectedItemIndexDone] = useState(null);

  useEffect(() => {
    const storedToDoItems = localStorage.getItem('toDoItems');
    const storedDoneItems = localStorage.getItem('doneItems');

    if (storedToDoItems) {
      setToDoItems(JSON.parse(storedToDoItems));
    }

    if (storedDoneItems) {
      setDoneItems(JSON.parse(storedDoneItems));
    }
  }, []);

  const addToDoItem = item => {
    setToDoItems([...toDoItems, item]);
  };

  const addDoneItem = item => {
    setDoneItems([...doneItems, item]);
  };

  useEffect(() => {
    localStorage.setItem('toDoItems', JSON.stringify(toDoItems));
  }, [toDoItems]);

  useEffect(() => {
    localStorage.setItem('doneItems', JSON.stringify(doneItems));
  }, [doneItems]);

  const [backlogItems, setBacklogItems] = useState([]);

  useEffect(() => {
    const storedBacklogItems = localStorage.getItem('backlogItems');
    if (storedBacklogItems) {
      setBacklogItems(JSON.parse(storedBacklogItems));
    }
  }, []);

  useEffect(() => {
    localStorage.setItem('backlogItems', JSON.stringify(backlogItems));
  }, [backlogItems]);

  useEffect(() => {
    const toDoBacklogItems = backlogItems.filter(item => item.workflow === 'todo');
    const doneBacklogItems = backlogItems.filter(item => item.workflow === 'done');
    setToDoItems(toDoBacklogItems.map(item => item.description));
    setDoneItems(doneBacklogItems.map(item => item.description));
    localStorage.setItem('backlogItems', JSON.stringify(backlogItems));
  }, [backlogItems]);

  const toggleDropdown = (index, isToDoList) => {
    if (isToDoList) {
      setDropdownOpenToDo(!dropdownOpenToDo);
      setSelectedItemIndexToDo(index);
    } else {
      setDropdownOpenDone(!dropdownOpenDone);
      setSelectedItemIndexDone(index);
    }
  };

  const deleteItem = isToDoList => {
    if (isToDoList) {
      const updatedToDoItems = [...toDoItems];
      updatedToDoItems.splice(selectedItemIndexToDo, 1);
      setToDoItems(updatedToDoItems);
      setDropdownOpenToDo(false);
      const updatedBacklogItems = backlogItems.filter(
        item => item.workflow !== 'todo' || item.description !== toDoItems[selectedItemIndexToDo]
      );
      setBacklogItems(updatedBacklogItems);
    } else {
      const updatedDoneItems = [...doneItems];
      updatedDoneItems.splice(selectedItemIndexDone, 1);
      setDoneItems(updatedDoneItems);
      setDropdownOpenDone(false);
      const updatedBacklogItems = backlogItems.filter(
        item => item.workflow !== 'done' || item.description !== doneItems[selectedItemIndexDone]
      );
      setBacklogItems(updatedBacklogItems);
    }
  };

  return (
    <Container>
      <Row>
        <Col>
          <div className="p-3 mb-3 bg-light border">
            <h4>To Do</h4>

            <div>
              {toDoItems.map((item, index) => (
                <div key={index} className={`item-container to-do-container`}>
                  {item}
                  <Dropdown isOpen={dropdownOpenToDo && selectedItemIndexToDo === index} toggle={() => toggleDropdown(index, true)}>
                    <DropdownToggle className="dropdown-toggle" tag="span">
                      <FontAwesomeIcon icon={faEllipsisV} />
                    </DropdownToggle>
                    <DropdownMenu right>
                      <DropdownItem onClick={deleteItem}>
                        <FontAwesomeIcon icon={faTrash} /> Delete
                      </DropdownItem>
                    </DropdownMenu>
                  </Dropdown>
                </div>
              ))}
            </div>
          </div>
        </Col>
        <Col>
          <div className="p-3 mb-3 bg-light border">
            <h4>Done</h4>
            <div>
              {doneItems.map((item, index) => (
                <div key={index} className={`item-container done-container`}>
                  {item}
                  <Dropdown isOpen={dropdownOpenDone && selectedItemIndexDone === index} toggle={() => toggleDropdown(index, false)}>
                    <DropdownToggle className="dropdown-toggle" tag="span">
                      <FontAwesomeIcon icon={faEllipsisV} />
                    </DropdownToggle>
                    <DropdownMenu right>
                      <DropdownItem onClick={() => deleteItem(false)}>
                        <FontAwesomeIcon icon={faTrash} /> Delete
                      </DropdownItem>
                    </DropdownMenu>
                  </Dropdown>
                </div>
              ))}
            </div>
          </div>
        </Col>
      </Row>
    </Container>
  );
};

export default Board;
