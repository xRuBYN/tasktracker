import dayjs from 'dayjs';

export interface IBoard {
  id?: string;
  name?: string;
  createdDate?: string;
  createdBy?: string;
  lastModifiedBy?: string | null;
  lastModifiedDate?: string | null;
}

export const defaultValue: Readonly<IBoard> = {};
