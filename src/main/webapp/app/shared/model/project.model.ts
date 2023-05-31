import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';
import { IBoard } from 'app/shared/model/board.model';

export interface IProject {
  id?: string;
  name?: string;
  createdDate?: string;
  createdBy?: string;
  lastModifiedBy?: string | null;
  lastModifiedDate?: string | null;
  user?: IUser | null;
  board?: IBoard | null;
  users?: IUser[] | null;
}

export const defaultValue: Readonly<IProject> = {};
