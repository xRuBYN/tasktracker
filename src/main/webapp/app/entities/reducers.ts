import project from 'app/entities/project/project.reducer';
import board from 'app/entities/board/board.reducer';
import columnEntity from 'app/entities/column-entity/column-entity.reducer';
import issue from 'app/entities/issue/issue.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  project,
  board,
  columnEntity,
  issue,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
