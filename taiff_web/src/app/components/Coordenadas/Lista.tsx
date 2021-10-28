import React, { useState } from 'react';
import ListItem from './ListItem'
import NewTaskInput from './CoordenadaTeste'
import { coordenadaInput } from '../../interfaces/coordenadaInterface'

const Lista = () => {

    const [tasks, setTasks] = useState<coordenadaInput[]>([]);

    function addNewTask(task: coordenadaInput) {
        const itensCopy = Array.from(tasks);
        itensCopy.push({
            coordenada_x: task.coordenada_x,
            coordenada_y: task.coordenada_y,
            coordenada_z: task.coordenada_z,
            eixo_r: task.eixo_r,
            tempo: task.tempo,
            zero_peca: task.zero_peca
        });
        setTasks(itensCopy);
    }

    function updateTask(target: any, index: number) {
       // console.log(target);

        const itensCopy = Array.from(tasks);

        switch (target.target.name.split("_")[0]) {
            case "coordenadaX":
                itensCopy.splice(index, 1, {
                    ...tasks[index],
                    coordenada_x: parseInt(target.target.value)
                });
                setTasks(itensCopy);
                break;
            case "coordenadaY":
                itensCopy.splice(index, 1, {
                    ...tasks[index],
                    coordenada_y: parseInt(target.target.value)
                });
                setTasks(itensCopy);
                break;
            case "coordenadaZ":
                itensCopy.splice(index, 1, {
                    ...tasks[index],
                    coordenada_z: parseInt(target.target.value)
                });
                setTasks(itensCopy);
                break;
            case "eixoR":
                itensCopy.splice(index, 1, {
                    ...tasks[index],
                    eixo_r: parseInt(target.target.value)
                });
                setTasks(itensCopy);
                break;
            case "tempo":
                itensCopy.splice(index, 1, {
                    ...tasks[index],
                    tempo: parseInt(target.target.value)
                });
                setTasks(itensCopy);
                break;
            default: 
                console.log(target.target.checked)
                itensCopy.splice(index, 1, {
                    ...tasks[index],
                    zero_peca: target.target.checked
                });
                setTasks(itensCopy);
                break;
        }

    }

    function deleteTask(index: number) {
        const itensCopy = Array.from(tasks);
        itensCopy.splice(index, 1);
        setTasks(itensCopy);
    }

    return (
        <div className="App">
            <div className="App-header">
                <NewTaskInput onSubmit={addNewTask} />
                {tasks.map((coordenada: coordenadaInput, index: number) => {
                    //console.log(coordenada);

                    return (
                        <ListItem
                            key={index}
                            index={index}
                            value={coordenada}
                            onChange={(event: any) => updateTask(event, index)}
                            onDelete={() => deleteTask(index)}
                        />
                    )
                })}
            </div>
        </div>
    )
}

export default Lista;