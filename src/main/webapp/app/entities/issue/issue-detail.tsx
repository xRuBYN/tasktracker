import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './issue.reducer';

export const IssueDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const issueEntity = useAppSelector(state => state.issue.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="issueDetailsHeading">
          <Translate contentKey="tasktrackerApp.issue.detail.title">Issue</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="tasktrackerApp.issue.id">Id</Translate>
            </span>
          </dt>
          <dd>{issueEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tasktrackerApp.issue.name">Name</Translate>
            </span>
          </dt>
          <dd>{issueEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="tasktrackerApp.issue.description">Description</Translate>
            </span>
          </dt>
          <dd>{issueEntity.description}</dd>
          <dt>
            <span id="priority">
              <Translate contentKey="tasktrackerApp.issue.priority">Priority</Translate>
            </span>
          </dt>
          <dd>{issueEntity.priority}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="tasktrackerApp.issue.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>{issueEntity.createdDate ? <TextFormat value={issueEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="tasktrackerApp.issue.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{issueEntity.createdBy}</dd>
          <dt>
            <span id="lastModifiedBy">
              <Translate contentKey="tasktrackerApp.issue.lastModifiedBy">Last Modified By</Translate>
            </span>
          </dt>
          <dd>{issueEntity.lastModifiedBy}</dd>
          <dt>
            <span id="lastModifiedDate">
              <Translate contentKey="tasktrackerApp.issue.lastModifiedDate">Last Modified Date</Translate>
            </span>
          </dt>
          <dd>
            {issueEntity.lastModifiedDate ? <TextFormat value={issueEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="tasktrackerApp.issue.column">Column</Translate>
          </dt>
          <dd>{issueEntity.column ? issueEntity.column.id : ''}</dd>
          <dt>
            <Translate contentKey="tasktrackerApp.issue.assigned">Assigned</Translate>
          </dt>
          <dd>{issueEntity.assigned ? issueEntity.assigned.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/issue" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/issue/${issueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default IssueDetail;
