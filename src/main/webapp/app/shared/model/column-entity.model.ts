import dayjs from 'dayjs';
import { IBoard } from 'app/shared/model/board.model';

export interface IColumnEntity {
  id?: string;
  name?: string;
  createdDate?: string;
  createdBy?: string;
  lastModifiedBy?: string | null;
  lastModifiedDate?: string | null;
  board?: IBoard | null;
}

export const defaultValue: Readonly<IColumnEntity> = {};
