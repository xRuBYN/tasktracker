import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './project.reducer';

export const ProjectDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const projectEntity = useAppSelector(state => state.project.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="projectDetailsHeading">
          <Translate contentKey="tasktrackerApp.project.detail.title">Project</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="tasktrackerApp.project.id">Id</Translate>
            </span>
          </dt>
          <dd>{projectEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tasktrackerApp.project.name">Name</Translate>
            </span>
          </dt>
          <dd>{projectEntity.name}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="tasktrackerApp.project.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>
            {projectEntity.createdDate ? <TextFormat value={projectEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="tasktrackerApp.project.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{projectEntity.createdBy}</dd>
          <dt>
            <span id="lastModifiedBy">
              <Translate contentKey="tasktrackerApp.project.lastModifiedBy">Last Modified By</Translate>
            </span>
          </dt>
          <dd>{projectEntity.lastModifiedBy}</dd>
          <dt>
            <span id="lastModifiedDate">
              <Translate contentKey="tasktrackerApp.project.lastModifiedDate">Last Modified Date</Translate>
            </span>
          </dt>
          <dd>
            {projectEntity.lastModifiedDate ? (
              <TextFormat value={projectEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <Translate contentKey="tasktrackerApp.project.user">User</Translate>
          </dt>
          <dd>{projectEntity.user ? projectEntity.user.id : ''}</dd>
          <dt>
            <Translate contentKey="tasktrackerApp.project.users">Users</Translate>
          </dt>
          <dd>
            {projectEntity.users
              ? projectEntity.users.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {projectEntity.users && i === projectEntity.users.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/project" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/project/${projectEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProjectDetail;
