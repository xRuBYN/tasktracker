import './home.scss';

import React, { useState } from 'react';
import { Translate } from 'react-jhipster';
import { Row, Col, Alert, Button, Modal, ModalHeader, ModalBody, ModalFooter, Form, FormGroup, Label, Input } from 'reactstrap';
import { useAppSelector } from 'app/config/store';
import { TranslatorContext } from 'react-jhipster';
import { Link, useNavigate } from 'react-router-dom';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);
  const [modal, setModal] = useState(false);
  const [projectName, setProjectName] = useState('');
  const [recentProjects, setRecentProjects] = useState([]);
  const navigate = useNavigate();

  const toggleModal = () => {
    setModal(!modal);
  };

  const handleProjectNameChange = event => {
    setProjectName(event.target.value);
  };

  const handleSubmit = event => {
    event.preventDefault();
    const newProject = {
      projectName,
    };
    setRecentProjects([newProject, ...recentProjects]);
    navigate('/backlog');
    toggleModal();
  };

  return (
    <Row>
      <Col md="6">
        <h2>
          <Translate contentKey="home.title">Welcome, {account.login}!</Translate>
        </h2>
        <hr />
        <Alert color="info">
          <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
            You are logged in as user {account.login}.
          </Translate>
        </Alert>
        <Button color="primary" onClick={toggleModal}>
          <Translate contentKey="home.createProject">Create Project</Translate>
        </Button>
      </Col>
      <Modal isOpen={modal} toggle={toggleModal}>
        <ModalHeader toggle={toggleModal}>
          <Translate contentKey="home.createProject">Create Project</Translate>
        </ModalHeader>
        <ModalBody>
          <Form onSubmit={handleSubmit}>
            <FormGroup>
              <Label for="projectName">
                <Translate contentKey="home.projectName">Project Name</Translate>
              </Label>
              <Input type="text" name="projectName" id="projectName" value={projectName} onChange={handleProjectNameChange} required />
            </FormGroup>
            <ModalFooter>
              <Button color="primary" type="submit">
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
              <Button color="secondary" onClick={toggleModal}>
                <Translate contentKey="entity.action.cancel">Cancel</Translate>
              </Button>
            </ModalFooter>
          </Form>
        </ModalBody>
      </Modal>

      <Col md="6">
        <h3>
          <Translate contentKey="home.recentProjects">Recent Projects</Translate>
        </h3>
        <ul>
          {recentProjects.map((project, index) => (
            <li key={index}>
              <strong>Project Name:</strong> {project.projectName}
            </li>
          ))}
        </ul>
      </Col>
    </Row>
  );
};

TranslatorContext.setDefaultLocale('en');
TranslatorContext.setRenderInnerTextForMissingKeys(false);

export default Home;
