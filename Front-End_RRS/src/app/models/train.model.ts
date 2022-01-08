export class TrainModel
{
    constructor(
        public trainNo?:string,
        public trainName?:string,
        public source?:string,
        public destination?:string,
        public seats_Available?:string,
        public fare?:string
    )
    {}
}