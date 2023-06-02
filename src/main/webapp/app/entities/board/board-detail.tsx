import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './board.reducer';

export const BoardDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const boardEntity = useAppSelector(state => state.board.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="boardDetailsHeading">
          <Translate contentKey="tasktrackerApp.board.detail.title">Board</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="tasktrackerApp.board.id">Id</Translate>
            </span>
          </dt>
          <dd>{boardEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tasktrackerApp.board.name">Name</Translate>
            </span>
          </dt>
          <dd>{boardEntity.name}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="tasktrackerApp.board.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>{boardEntity.createdDate ? <TextFormat value={boardEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="tasktrackerApp.board.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{boardEntity.createdBy}</dd>
          <dt>
            <span id="lastModifiedBy">
              <Translate contentKey="tasktrackerApp.board.lastModifiedBy">Last Modified By</Translate>
            </span>
          </dt>
          <dd>{boardEntity.lastModifiedBy}</dd>
          <dt>
            <span id="lastModifiedDate">
              <Translate contentKey="tasktrackerApp.board.lastModifiedDate">Last Modified Date</Translate>
            </span>
          </dt>
          <dd>
            {boardEntity.lastModifiedDate ? <TextFormat value={boardEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/board" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/board/${boardEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BoardDetail;
