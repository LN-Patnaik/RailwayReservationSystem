export class RoleModel{
    constructor(
        public roleId?:string,
        public role?:string
    ){}

    setRole(role:string){
        return this.role=role;
    }

    setRoleId(id:string){
        this.roleId=id;
    }
}