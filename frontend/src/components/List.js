import Task from "./Task";

export default function List({ list, actions }) {
    const { id, title, tasks } = list;

    return (
        <div className="tf-list rounded" data-id={id}>

              <div className="tf-list-header">
                <input type="text" className="tf-list-title" value={title} autoComplete="off" onChange={(event) => event.target.value}
          onBlur={(event) => actions.onBlur(id, event.target.value)}></input>
              </div>
              
              <div className="tf-list-tasks">

                {tasks.map(task => (
                  <Task key={task.id} task={task}></Task>
                ))}
              </div>

              <div className="tf-list-add">
                <button className="tf-btn-add rounded"><i className="bi bi-plus-circle-fill"></i> New Card</button>
              </div>

        </div>
    )
}