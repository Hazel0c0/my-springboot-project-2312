const TodoMain = ({ todoList, remove, check }) => {

  // console.log(bbb.todoList);


return (
  <ul className='todo-list'>
      {
          todoList.map(todo => <TodoItem 
                                  key={todo.id} 
                                  item={todo} 
                                  remove={remove} 
                                  check={check}
                                />)
      }
  </ul>
)
}

export default TodoMain
