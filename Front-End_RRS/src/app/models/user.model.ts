import { RoleModel } from "./role.model";

export class UserModel {
    
        public userId?: number;
        public username?: string;
        public password?: string;
        public roles: RoleModel[]=[];
        public email?: string;
        public phoneNumber?:string;
        public token?: string;
    
}