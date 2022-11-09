export function averageNotes(nota){

  if(notas.length <1){
    return 0;
  }

  return  (notas.reduce((soma, nota)=> soma+nota) / notas.length).toFixed(2);

}